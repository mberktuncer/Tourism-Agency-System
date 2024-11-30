package main.service;

import main.helper.Config;
import main.helper.GUIHelper;
import main.model.User;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserService {

    public static ArrayList<User> listAll(){
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        User user;
        try(Connection ignored = Config.connect()){
            Statement statement = Config.connect().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public static User getUserById(int id){
        String query = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection ignored = Config.connect()){
            PreparedStatement preparedStatement = Config.connect().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;

    }

    public static User getUserByUserName(String userName){
        String query = "SELECT * FROM users WHERE userName = ?";
        User user = null;

        try (Connection ignored = Config.connect()){
            PreparedStatement preparedStatement = Config.connect().prepareStatement(query);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;

    }

    public static boolean add(String name, String surname, String userName, String password, String role){
        String query = "INSERT INTO users (name, surname, username, password, role) VALUES (?, ?, ?, ?, ?::user_role)";
        User user = getUserByUserName(userName);
        if (user != null){
            return false;
        }
        try{
            PreparedStatement preparedStatement = Config.connect().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, role);
            int result = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + result);
            return result == 1;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static List<String> getUserRoles(){
        List<String> roles = new ArrayList<>();
        String query = "SELECT unnest(enum_range(NULL::user_role))";

        try(Connection ignored = Config.connect()){
            Statement statement = Config.connect().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                roles.add(resultSet.getString(1));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return roles;
    }

    public static List<User> getUsersByRole(String role){
        String query = "SELECT * FROM users WHERE role = ?::user_role";
        User user;
        ArrayList<User> users = new ArrayList<>();

        try (Connection ignored = Config.connect()){
            PreparedStatement preparedStatement = Config.connect().prepareStatement(query);
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return users;

    }

    public static boolean update(String name, String surname, String userName, String role){
        String query = "UPDATE users SET name = ?, surname = ?, username = ?, role = ? WHERE username = ?";
        User user = getUserByUserName(userName);
        if (user != null && !Objects.equals(user.getUserName(), userName)){
            GUIHelper.showMessage("This username has been used before");
            return false;
        }
        try(Connection ignored = Config.connect()){
            PreparedStatement preparedStatement = Config.connect().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, role);
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean deleteById(int id){
        String query = "DELETE FROM users WHERE id = ?";
        try(Connection ignored = Config.connect()){
            PreparedStatement preparedStatement = Config.connect().prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
