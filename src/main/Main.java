package main;

import main.helper.Config;
import main.model.User;
import main.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = UserService.listAll();

        for (User user : users){
            System.out.println(user.toString());
        }

    }
}