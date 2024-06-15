package Model;

import View.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Charani
 */
public class RoomModel {
    private Connection con;
    private PreparedStatement pst;
    private Room view;

    public RoomModel() {
        try {
            con = DBConnection.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RoomModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void setView(Room view) {
        this.view = view;
    }

    public DefaultTableModel loadRooms() {
        DefaultTableModel d = new DefaultTableModel();
        try {
            pst = con.prepareStatement("SELECT * FROM room");
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData rsd = rs.getMetaData();
            int c = rsd.getColumnCount();

            // Add columns to table model
            for (int i = 1; i <= c; i++) {
                d.addColumn(rsd.getColumnName(i));
            }

            // Add data rows to table model
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= c; i++) {
                    v.add(rs.getString(i));
                }
                d.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public void saveRoom(String roomNo, String roomCondition, String roomType, String bedType, String amount) {
        try {
            pst = con.prepareStatement("INSERT INTO room (Roomid, Roomcondition, Roomtype, Bedtype, Price) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, roomNo);
            pst.setString(2, roomCondition);
            pst.setString(3, roomType);
            pst.setString(4, bedType);
            pst.setString(5, amount);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editRoom(String roomNo, String roomCondition, String roomType, String bedType, String amount) {
        try {
            pst = con.prepareStatement("UPDATE room SET Roomcondition=?, Roomtype=?, Bedtype=?, Price=? WHERE Roomid=?");
            pst.setString(1, roomCondition);
            pst.setString(2, roomType);
            pst.setString(3, bedType);
            pst.setString(4, amount);
            pst.setString(5, roomNo);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRoom(String roomNo) {
        try {
            pst = con.prepareStatement("DELETE FROM room WHERE Roomid=?");
            pst.setString(1, roomNo);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String autoId() {
        String newRoomId = null;
        try {
            pst = con.prepareStatement("SELECT MAX(Roomid) FROM room");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String maxRoomId = rs.getString(1);
                if (maxRoomId == null) {
                    newRoomId = "R001";
                } else {
                    int id = Integer.parseInt(maxRoomId.substring(1)) + 1;
                    newRoomId = String.format("R%03d", id);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newRoomId;
    }
    
    public void loadRoomData(String roomNo) {
    try {
        pst = con.prepareStatement("SELECT * FROM room WHERE Roomid = ?");
        pst.setString(1, roomNo);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String roomCondition = rs.getString("Roomcondition");
            String roomType = rs.getString("Roomtype");
            String bedType = rs.getString("Bedtype");
            String amount = rs.getString("Price");

            view.setRoomNo(roomNo); 
            view.setRoomCondition(roomCondition);
            view.setRoomType(roomType);
            view.setBedType(bedType);
            view.setAmount(amount);
        }
    } catch (SQLException ex) {
        Logger.getLogger(RoomModel.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}


