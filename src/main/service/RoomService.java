package main.service;

import main.helper.Config;
import main.helper.GUIHelper;
import main.model.room.Room;

import java.sql.*;
import java.util.ArrayList;

public class RoomService {

    public static ArrayList<Room> listAll(){
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room";
        Room room;
        try(Connection connection = Config.connect()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setRoomType(resultSet.getString("room_type"));
                room.setBedCount(resultSet.getInt("bed_count"));
                room.setSquareMeters(resultSet.getInt("square_meters"));
                room.setStock(resultSet.getInt("stock"));
                rooms.add(room);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rooms;
    }

    public static Room getRoomByRoomTypeHotelId(String roomType, int hotelId){
        String query = "SELECT * FROM room WHERE room_type = ? AND hotel_id = ?";
        Room room = null;
        try(Connection connection = Config.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, roomType);
            preparedStatement.setInt(2, hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setRoomType(resultSet.getString("room_type"));
                room.setBedCount(resultSet.getInt("bed_count"));
                room.setSquareMeters(resultSet.getInt("square_meters"));
                room.setStock(resultSet.getInt("stock"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return room;
    }

    public static boolean add(Room room){
        String query = "INSERT INTO room (hotel_id, room_type, bed_count, square_meters, stock)" +
                "VALUES (?, ?, ?, ?, ?) RETURNING id";
        Room findRoom = getRoomByRoomTypeHotelId(room.getRoomType(), room.getHotelId());
        if ((findRoom != null) && (findRoom.getRoomType().equals(room.getRoomType())) && (findRoom.getHotelId() == room.getHotelId())){
            GUIHelper.showMessage("This room already added");
            return false;
        }

        try(Connection connection = Config.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setString(2, room.getRoomType());
            preparedStatement.setInt(3, room.getBedCount());
            preparedStatement.setInt(4, room.getSquareMeters());
            preparedStatement.setInt(5, room.getStock());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int generatedId = rs.getInt("id"); // Dönen id sütunu alınır
                room.setId(generatedId); // Room nesnesine ID set edilir
                return true; // Ekleme işlemi başarılı
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(Room room){
        String query = "UPDATE room set hotel_id = ?, room_type = ?, bed_count = ?, square_meters = ?, stock = ? " +
                "WHERE id = ?";
        try(Connection connection = Config.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setString(2, room.getRoomType());
            preparedStatement.setInt(3, room.getBedCount());
            preparedStatement.setInt(4, room.getSquareMeters());
            preparedStatement.setInt(5, room.getStock());
            preparedStatement.setInt(6, room.getId());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
