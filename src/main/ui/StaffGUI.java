package main.ui;

import main.helper.Constants;
import main.helper.GUIHelper;
import main.model.hotel.Hotel;
import main.service.HotelService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StaffGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_top;
    private JTabbedPane tab_hotel;
    private JTable tbl_hotel_list;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;

    public StaffGUI(){
        GUIHelper.setLookAndFeel();
        initializeGUI();

        String[] columnHotelList = {"Id", "Name", "Address", "Email", "Phone Number", "Star", "Boarding House Type", "Facility Features"};
        mdl_hotel_list = GUIHelper.createCustomTableModel(columnHotelList, 0);
        row_hotel_list = new Object[columnHotelList.length];

        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);

        loadHotelModel();
    }

    private void initializeGUI() {
        add(wrapper);
        setSize(1500, 500);
        GUIHelper.centerFrame(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.WINDOW_TITLE);
        setVisible(true);
    }

    private void loadHotelModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);

        List<Hotel> hotels = HotelService.listAll();

        for (Hotel hotel : hotels){
            int i = 0;
            row_hotel_list[i++] = hotel.getId();
            row_hotel_list[i++] = hotel.getName();
            row_hotel_list[i++] = hotel.getAddress();
            row_hotel_list[i++] = hotel.getEmail();
            row_hotel_list[i++] = hotel.getPhoneNumber();
            row_hotel_list[i++] = hotel.getStar();
            row_hotel_list[i++] = hotel.getBoardingHouseType();
            row_hotel_list[i++] = hotel.getFacilityFeatures();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    public static void main(String[] args) {
        GUIHelper.setLookAndFeel();
        StaffGUI staffGUI = new StaffGUI();

    }
}
