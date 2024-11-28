package main;

import main.helper.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(Connection connection = Config.connect()){
            System.out.println("Database connection success");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}