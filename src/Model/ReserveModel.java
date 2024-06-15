package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Charani
 */
public class ReserveModel {
    private final Connection connection;
    
    public ReserveModel() throws SQLException, ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }
    
    public String generateNextReservationId() throws SQLException {
    String nextId = "";
    try {
        PreparedStatement pst = connection.prepareStatement("SELECT MAX(Reservationid) FROM reservation");
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String currentMaxId = rs.getString(1);
            if (currentMaxId == null) {
                nextId = "RE001";
            } else {
                int numericPart = Integer.parseInt(currentMaxId.substring(2)) + 1;
                nextId = String.format("RE%03d", numericPart);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReserveModel.class.getName()).log(Level.SEVERE, null, ex);
        throw ex;
    }
    return nextId;
}

    
    public boolean addReservation(String reno, String name, String address, String mobile, String checkin, String checkout, String roomcondition, String roomtype, String bedtype) {
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO reservation (Reservationid, Name, Address, Mobile, Checkin, Checkout, Roomcondition, Roomtype, Bedtype) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, reno);
            pst.setString(2, name);
            pst.setString(3, address);
            pst.setString(4, mobile);
            pst.setString(5, checkin);
            pst.setString(6, checkout);
            pst.setString(7, roomcondition);
            pst.setString(8, roomtype);
            pst.setString(9, bedtype);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReserveModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean editReservation(String reno, String name, String address, String mobile, String roomcondition, String roomtype, String bedtype) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE reservation SET Name=?, Address=?, Mobile=?, Roomcondition=?, Roomtype=?, Bedtype=? WHERE Reservationid=?");
        pst.setString(1, name);
        pst.setString(2, address);
        pst.setString(3, mobile);
        pst.setString(4, roomcondition);
        pst.setString(5, roomtype);
        pst.setString(6, bedtype);
        pst.setString(7, reno);
        pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReserveModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteReservation(String reno) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM reservation WHERE Reservationid=?");
            pst.setString(1, reno);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReserveModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<String[]> loadReservations() {
        List<String[]> reservations = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM reservation");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String[] reservation = {
                    rs.getString("Reservationid"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Mobile"),
                    rs.getString("Checkin"),
                    rs.getString("Checkout"),
                    rs.getString("Roomcondition"),
                    rs.getString("Roomtype"),
                    rs.getString("Bedtype")
                };
                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserveModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservations;
    }
}
