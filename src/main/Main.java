package main;

import main.helper.Config;
import main.model.User;
import main.service.UserService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        UserService.add("Berk", "Tuncer", "thetestere", "123", "admin");


    }
}