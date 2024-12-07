package main.ui;

import main.helper.Constants;
import main.helper.GUIHelper;
import main.model.room.Room;
import main.service.ReservationService;
import main.service.RoomService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MakeReservationGUI extends JFrame{
    private JPanel panel1;
    private JPanel wrapper;
    private JTextField fld_room_id;
    private JTextField fld_customer_name;
    private JTextField fld_customer_surname;
    private JTextField fld_customer_id_no;
    private JTextField fld_check_in;
    private JTextField fld_check_out;
    private JTextField fld_number_of_adult;
    private JTextField fld_number_of_child;
    private JTextField fld_total_price;
    private JButton btn_make_reservation;

    public MakeReservationGUI(){
        initializeGUI();
        initializeEvents();
    }

    public void initializeGUI(){
        GUIHelper.setLookAndFeel();
        add(wrapper);
        setSize(400, 400);
        GUIHelper.centerFrame(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.WINDOW_TITLE);
        setVisible(true);
    }


    public void initializeEvents(){
        btn_make_reservation.addActionListener(e -> {
            int roomId = Integer.parseInt(fld_room_id.getText());
            String customerName = fld_customer_name.getText();
            String customerSurname = fld_customer_surname.getText();
            String customerIdentityNumber = fld_customer_id_no.getText();
            String checkInString = fld_check_in.getText();
            String checkOutString = fld_check_out.getText();
            double numberOfAdult = Double.parseDouble(fld_number_of_adult.getText());
            double numberOfChildren = Double.parseDouble(fld_number_of_child.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate checkInDate = LocalDate.parse(checkInString, formatter);
            LocalDate checkOutDate = LocalDate.parse(checkOutString, formatter);


        });
    }

    public static void main(String[] args) {
        GUIHelper.setLookAndFeel();
        MakeReservationGUI makeReservationGUI = new MakeReservationGUI();
    }
}
