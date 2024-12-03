package main.ui;

import main.helper.Constants;
import main.helper.GUIHelper;
import main.model.room.Room;
import main.service.HotelService;
import main.service.RoomService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomGUI extends JFrame{
    private JPanel panel1;
    private JPanel wrapper;
    private JTextField fld_hotel_id;
    private JTextField fld_bed_count;
    private JTextField fld_sqr_meters;
    private JTextField fld_stock;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JButton addButton;
    private JButton cancelButton;
    private JComboBox cmb_room_type;
    private StaffGUI staffGUI;

    public AddRoomGUI(StaffGUI staffGUI){
        this.staffGUI = staffGUI;
        initializeGUI();
        initializeEvents();
    }

    private void initializeGUI() {
        GUIHelper.setLookAndFeel();
        add(wrapper);
        setSize(500, 400);
        GUIHelper.centerFrame(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.WINDOW_TITLE);
        setVisible(true);
    }

    private void initializeEvents() {
        cancelButton.addActionListener(e -> {
            dispose();
        });
        addButton.addActionListener(e -> {
            Room room = new Room();
            room.setHotelId(Integer.parseInt(fld_hotel_id.getText()));
            room.setRoomType(cmb_room_type.getSelectedItem().toString());
            room.setBedCount(Integer.parseInt(fld_bed_count.getText()));
            room.setSquareMeters(Integer.parseInt(fld_sqr_meters.getText()));
            room.setStock(Integer.parseInt(fld_stock.getText()));
            room.setAdultPrice(Double.parseDouble(fld_adult_price.getText()));
            room.setChildPrice(Double.parseDouble(fld_child_price.getText()));

            if (fld_hotel_id.getText().isEmpty() || fld_bed_count.getText().isEmpty()
                    || fld_sqr_meters.getText().isEmpty() || fld_stock.getText().isEmpty()
                    || fld_adult_price.getText().isEmpty() || fld_child_price.getText().isEmpty()){
                GUIHelper.showMessage(Constants.MSG_FILL);
            }
            else{
                if (GUIHelper.confirm(Constants.MSG_SURE)){
                    if (RoomService.add(room)){
                        GUIHelper.showMessage(Constants.MSG_DONE);
                    }
                    else{
                        GUIHelper.showMessage(Constants.MSG_ERROR);
                    }
                }
            }

            fld_hotel_id.setText(null);
            fld_bed_count.setText(null);
            fld_sqr_meters.setText(null);
            fld_stock.setText(null);
            fld_adult_price.setText(null);
            fld_child_price.setText(null);
            staffGUI.loadRoomModel(RoomService.listAll());

        });
    }

}
