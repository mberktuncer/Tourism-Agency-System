package main;


import main.model.User;
import main.model.hotel.Hotel;
import main.service.HotelService;
import main.service.UserService;

public class Main {
    public static void main(String[] args) {

        for (Hotel hotel : HotelService.listAll()){
            System.out.println(hotel.toString());
        }
    }
}