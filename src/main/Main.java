package main;


import main.model.Reservation;
import main.model.ResultDetails;
import main.model.User;
import main.model.hotel.BoardingHouseType;
import main.model.hotel.FacilityFeatures;
import main.model.hotel.Hotel;
import main.model.room.Room;
import main.model.room.RoomDetails;
import main.model.room.RoomFeaturesEnum;
import main.service.HotelService;
import main.service.ReservationService;
import main.service.RoomService;
import main.service.UserService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.service.RoomFeaturesService.add;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate checkInDate = LocalDate.parse("01/06/2021", formatter);
        LocalDate checkOutDate = LocalDate.parse("03/06/2021", formatter);

        for (Reservation reservation : ReservationService.listAll()){
            System.out.println(reservation.toString());
        }
    }
}