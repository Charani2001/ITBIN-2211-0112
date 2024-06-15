package Controller;

import Model.DBConnection;
import Model.ReserveModel;
import View.Menu;
import View.Reserve;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Charani
 */
public class ReserveController {

    private ReserveModel model;
    private Reserve view;
    private Connection con;
    private PreparedStatement pst;
    private DefaultTableModel d;

    public ReserveController(Reserve view) {
        this.view = view;
        
        
        try {
            this.model = new ReserveModel();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReserveController.class.getName()).log(Level.SEVERE, null, ex);
        }

        connect();
        autoId();
        roomCon(); 
        roomType();
        bedType();
        
        loadReservation();
        
        view.addSaveButtonListener(new SaveButtonListener());
        view.addEditButtonListener(new EditButtonListener());
        view.addClearButtonListener(new ClearButtonListener());
        view.addDeleteButtonListener(new DeleteButtonListener());
        view.addTableMouseListener(new TableMouseListener());
        
    }

    public void connect() {
    try {
        this.con = DBConnection.getConnection();
    } catch (ClassNotFoundException | SQLException ex) {
        JOptionPane.showMessageDialog(view, "Failed to connect to the database.");
        System.exit(1); 
    }
}

    
    public void roomCon() {
        try {
            pst = (PreparedStatement) con.prepareStatement("select Distinct Roomcondition from room");
            ResultSet rs = pst.executeQuery();
            view.getCondition().removeAllItems();
            while (rs.next()) {
                view.getCondition().addItem(rs.getString("Roomcondition"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void roomType() {
        try {
            pst = (PreparedStatement) con.prepareStatement("select Distinct Roomtype from room");
            ResultSet rs = pst.executeQuery();
            view.getRoomType().removeAllItems();
            while (rs.next()) {
                view.getRoomType().addItem(rs.getString("Roomtype"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bedType() {
        try {
            pst = (PreparedStatement) con.prepareStatement("select Distinct Bedtype from room");
            ResultSet rs = pst.executeQuery();
            view.getBedType().removeAllItems();
            while (rs.next()) {
                view.getBedType().addItem(rs.getString("Bedtype"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void autoId() {
        try {
            String reservationId = model.generateNextReservationId();
            view.setReservationId(reservationId);
        } catch (SQLException ex) {
            Logger.getLogger(ReserveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadReservation() {
        List<String[]> reservations = model.loadReservations();
        DefaultTableModel tableModel = new DefaultTableModel();
        // Add column headers
        tableModel.addColumn("Reservation ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Address");
        tableModel.addColumn("Mobile");
        tableModel.addColumn("Check-in");
        tableModel.addColumn("Check-out");
        tableModel.addColumn("Room Condition");
        tableModel.addColumn("Room Type");
        tableModel.addColumn("Bed Type");
        
        for (String[] reservation : reservations) {
            tableModel.addRow(reservation);
        }
        view.setTableModel(tableModel);
}


    public class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String reservationId = view.getReserveID().getText();
            String name = view.getTxtName().getText();
            String address = view.getTxtAddress().getText();
            String mobile = view.getTxtMobile().getText();
            String chekindate = ((JTextField)view.getStartDate().getDateEditor().getUiComponent()).getText();
            String chekoutdate = ((JTextField)view.getEndDate().getDateEditor().getUiComponent()).getText();
            String roomCondition = (String)view.getCondition().getSelectedItem();
            String roomType = (String)view.getRoomType().getSelectedItem();
            String bedType = (String)view.getBedType().getSelectedItem();


boolean success = model.addReservation(reservationId, name, address, mobile, chekindate, chekoutdate, roomCondition, roomType, bedType);
if (success) {
    JOptionPane.showMessageDialog(view, "Reserved Successfully");
    //view.clearFields();
    autoId();
    loadReservation();
} else {
    JOptionPane.showMessageDialog(view, "Reservation failed");
}
        }
    }

    public class EditButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        String reservationId = view.getReserveID().getText();
        String name = view.getTxtName().getText();
        String address = view.getTxtAddress().getText();
        String mobile = view.getTxtMobile().getText();
        String roomCondition = (String)view.getCondition().getSelectedItem();
        String roomType = (String)view.getRoomType().getSelectedItem();
        String bedType = (String)view.getBedType().getSelectedItem();
        


boolean success = model.editReservation(reservationId, name, address, mobile, roomCondition, roomType, bedType);
if (success) {
    JOptionPane.showMessageDialog(view, "Reservation edited Successfully");
    view.clearFields();
    autoId();
    loadReservation();
} else {
    JOptionPane.showMessageDialog(view, "Failed to edit reservation");
}
    }
}


    public class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            view.clearFields();
        }
    }

    public class DeleteButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        String reservationId = view.getReserveID().getText();

        boolean success = model.deleteReservation(reservationId);
        if (success) {
            JOptionPane.showMessageDialog(view, "Reservation Deleted Successfully");
            view.clearFields();
            autoId();
            loadReservation();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to delete reservation");
        }
    }
}

public class TableMouseListener extends MouseAdapter {
    public void mouseClicked(MouseEvent evt) {
        int selectedRow = view.getSelectedTableRow();
        if (selectedRow != -1) {
            String reservationId = view.getReservationIdFromTable(selectedRow);
            String name = view.getNameFromTable(selectedRow);
            String address = view.getAddressFromTable(selectedRow);
            String mobile = view.getMobileFromTable(selectedRow);
            String roomCondition = view.getRoomConditionFromTable(selectedRow);
            String roomType = view.getRoomTypeFromTable(selectedRow);
            String bedType = view.getBedTypeFromTable(selectedRow);

            view.displaySelectedReservation(reservationId, name, address, mobile, roomCondition, roomType, bedType);
        }
    }
}


   
   
}
