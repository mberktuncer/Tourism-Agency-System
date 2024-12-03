package main.ui;

import main.helper.Constants;
import main.helper.GUIHelper;
import main.model.hotel.FacilityFeatures;
import main.model.hotel.Hotel;
import main.service.HotelService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StaffGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_top;
    private JTabbedPane tab_hotel;
    private JTable tbl_hotel_list;
    private JButton btn_logout;
    private JTextField fld_hotel_name;
    private JButton btn_search_hotel;
    private JButton btn_add_hotel;
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
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(40);
        tbl_hotel_list.getColumnModel().getColumn(5).setMaxWidth(40);
        tbl_hotel_list.getColumnModel().getColumn(4).setMaxWidth(150);

        loadHotelModel(HotelService.listAll());
        initializeEvents();


    }

    private void initializeGUI() {
        add(wrapper);
        setSize(1500, 500);
        GUIHelper.centerFrame(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.WINDOW_TITLE);
        setVisible(true);
    }

    private void initializeEvents(){
        btn_logout.addActionListener(e -> {
            dispose();
        });
        btn_search_hotel.addActionListener(e -> {
            String name = fld_hotel_name.getText();
            ArrayList<Hotel> search = HotelService.searchHotel(name);
            loadHotelModel(search);
            System.out.println("Search results size: " + search.size());
        });
        btn_add_hotel.addActionListener(e -> {
            AddHotelGUI addHotelGUI = new AddHotelGUI(this);
        });
    }

    protected void loadHotelModel(ArrayList<Hotel> hotels) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        hotels.forEach(this::addHotelToModel);
    }

    private void addHotelToModel(Hotel hotel) {
        DefaultTableModel model = (DefaultTableModel) tbl_hotel_list.getModel();
        Object[] row = {
                hotel.getId(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getEmail(),
                hotel.getPhoneNumber(),
                hotel.getStar(),
                formatEnum(hotel.getBoardingHouseType()),
                formatFeatures(hotel.getFacilityFeatures())
        };
        model.addRow(row);
    }

    private String formatEnum(Enum<?> enumValue) {
        String rawName = enumValue.name().toLowerCase().replace("_", " ");
        return Character.toUpperCase(rawName.charAt(0)) + rawName.substring(1);
    }

    private String formatFeatures(List<FacilityFeatures> features) {
        return features.stream()
                .map(this::formatEnum)
                .collect(Collectors.joining(", "));
    }


    public static void main(String[] args) {
        GUIHelper.setLookAndFeel();
        StaffGUI staffGUI = new StaffGUI();


    }
}
