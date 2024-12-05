package main.ui;

import main.helper.Constants;
import main.helper.GUIHelper;
import main.model.hotel.FacilityFeatures;
import main.model.hotel.Hotel;
import main.model.room.RoomDetails;
import main.service.HotelService;
import main.service.RoomService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JButton addNewRoomButton;
    private JTable tbl_room_list;
    private JTextField fld_rs_start_date;
    private JTextField fld_rs_end_date;
    private JTextField fld_rs_hotel_name;
    private JTextField fld_rs_city_name;
    private JButton btn_rs_search_button;
    private JTable tbl_rs_resut_list;
    private JButton btn_make_rs;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private DefaultTableModel mdl_room_list;
    private Object[] row_room_list;

    public StaffGUI(){

        initializeGUI();

        setHotelTable();
        setRoomTable();
        loadHotelModel(HotelService.listAll());
        loadRoomModel(RoomService.listAllDetails());


        initializeEvents();

    }

    private void initializeGUI() {
        GUIHelper.setLookAndFeel();
        add(wrapper);
        setSize(1500, 500);
        GUIHelper.centerFrame(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.WINDOW_TITLE);
        setVisible(true);
    }

    private void setHotelTable(){
        String[] columnHotelList = {"Id", "Name", "Address", "Email", "Phone Number", "Star", "Boarding House Type", "Facility Features"};
        mdl_hotel_list = GUIHelper.createCustomTableModel(columnHotelList, 0);
        row_hotel_list = new Object[columnHotelList.length];

        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(40);
        tbl_hotel_list.getColumnModel().getColumn(5).setMaxWidth(40);
        tbl_hotel_list.getColumnModel().getColumn(4).setMaxWidth(150);
    }

    private void setRoomTable() {
        String[] columnRoomList = {
                "Id", "Hotel Id", "Room Type", "Bed Count", "Square Meters",
                "Stock", "Adult Price", "Child Price", "Features"
        };
        mdl_room_list = GUIHelper.createCustomTableModel(columnRoomList, 0);
        row_room_list = new Object[columnRoomList.length];

        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(40);
        tbl_room_list.getColumnModel().getColumn(1).setMaxWidth(80);
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
        addNewRoomButton.addActionListener(e -> {
            AddRoomGUI addRoomGUI = new AddRoomGUI(this);
        });
    }

    protected void loadHotelModel(ArrayList<Hotel> hotels) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        hotels.forEach(this::addHotelToModel);
    }

    protected void loadRoomModel(ArrayList<RoomDetails> roomDetailsList) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        roomDetailsList.forEach(this::addRoomToModel);
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

    private void addRoomToModel(RoomDetails roomDetails) {
        DefaultTableModel model = (DefaultTableModel) tbl_room_list.getModel();
        Object[] row = {
                roomDetails.getRoomId(),
                roomDetails.getHotelId(),
                roomDetails.getRoomType(),
                roomDetails.getBedCount(),
                roomDetails.getSquareMeters(),
                roomDetails.getStock(),
                roomDetails.getAdultPrice(),
                roomDetails.getChildPrice(),
                String.join(", ", roomDetails.getRoomFeatures()) // Listeyi virgülle birleştir
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
