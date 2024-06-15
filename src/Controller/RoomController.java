package Controller;

import Model.RoomModel;
import View.Menu;
import View.Room;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Charani
 */
public class RoomController {
    private final Room view;
    private final RoomModel model;

    public RoomController(Room view) {
        this.view = view;
        this.model = new RoomModel();
        this.model.setView(view);
    }

    public void autoId() {
        String newRoomId = model.autoId();
        view.setRoomNo(newRoomId);
    }

    public void loadRooms() {
        DefaultTableModel rooms = model.loadRooms();
        view.getJTable1().setModel(rooms);
    }

    public void saveRoom() {
        String roomNo = view.getRoomNo();
        String roomCondition = view.getRoomCondition();
        String roomType = view.getRoomType();
        String bedType = view.getBedType();
        String amount = view.getAmount();
        model.saveRoom(roomNo, roomCondition, roomType, bedType, amount);
        JOptionPane.showMessageDialog(view, "Room Added Successfully");
        loadRooms();
    }

    public void editRoom() {
        String roomNo = view.getRoomNo();
        String roomCondition = view.getRoomCondition();
        String roomType = view.getRoomType();
        String bedType = view.getBedType();
        String amount = view.getAmount();
        model.editRoom(roomNo, roomCondition, roomType, bedType, amount);
        JOptionPane.showMessageDialog(view, "Room Edited Successfully");
        loadRooms();
    }

    public void deleteRoom() {
        String roomNo = view.getRoomNo();
        model.deleteRoom(roomNo);
        JOptionPane.showMessageDialog(view, "Room Deleted Successfully");
        loadRooms();
    }

    public void loadRoomData() {
      int selectedRow = view.getJTable1().getSelectedRow();
    if (selectedRow != -1) {
        String roomNo = view.getJTable1().getValueAt(selectedRow, 0).toString();
        model.loadRoomData(roomNo);
        System.out.println("Selected room: " + roomNo);
    } else {
        System.out.println("No row selected.");
    }
    }

    
    public void clearForm() {
    autoId();
    view.setRoomCondition(null);   
    view.setRoomType(null);       
    view.setBedType(null);        
    view.setAmount("");        
}

}


