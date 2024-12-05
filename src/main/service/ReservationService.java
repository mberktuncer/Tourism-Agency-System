package main.service;

import main.helper.DatabaseConfig;
import main.model.Reservation;
import main.model.room.RoomDetails;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    public static List<Reservation> listAll(){
        ArrayList<Reservation> reservations = new ArrayList<>();
         String query = "SELECT * FROM reservations";
         Reservation reservation;
         try (Connection connection= DatabaseConfig.connect()){
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             while (resultSet.next()){
                 reservation = new Reservation();
                 reservation.setId(resultSet.getInt("id"));
                 reservation.setRoomId(resultSet.getInt("room_id"));
                 reservation.setCustomerName(resultSet.getString("customer_name"));
                 reservation.setCustomerSurname(resultSet.getString("customer_surname"));
                 reservation.setCheckinDate(resultSet.getDate("check_in"));
                 reservation.setCheckoutDate(resultSet.getDate("check_out"));
                 reservation.setTotalPrice(resultSet.getDouble("total_price"));
                 reservations.add(reservation);
             }
         }catch (SQLException e){
             e.printStackTrace();
         }
         return reservations;
    }

    public static List<RoomDetails> searchRooms(String city, String hotelName, LocalDate checkIn, LocalDate checkOut) {
        String query = """
        SELECT 
            r.id AS room_id,
            h.name AS hotel_name,
            r.room_type,
            rp.adult_price,
            rp.child_price,
            r.stock,
            r.bed_count,
            r.square_meters
        FROM 
            room r
        JOIN 
            hotel h ON r.hotel_id = h.id
        JOIN 
            room_price rp ON r.id = rp.room_id
        WHERE 
            (h.address = ? OR ? IS NULL) AND
            (h.name = ? OR ? IS NULL) AND
            EXISTS (
                SELECT 1 
                FROM season s 
                WHERE s.id = rp.season_id 
                AND s.start_date <= ? AND s.end_date >= ?
            ) AND 
            r.stock > 0
        """;

        List<RoomDetails> roomDetailsList = new ArrayList<>();

        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, city);
            preparedStatement.setString(2, city);
            preparedStatement.setString(3, hotelName);
            preparedStatement.setString(4, hotelName);
            preparedStatement.setDate(5, Date.valueOf(checkIn));
            preparedStatement.setDate(6, Date.valueOf(checkOut));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RoomDetails roomDetails = new RoomDetails();
                roomDetails.setRoomId(resultSet.getInt("room_id"));
                roomDetails.setHotelName(resultSet.getString("hotel_name")); // Otel adı artık çekiliyor
                roomDetails.setRoomType(resultSet.getString("room_type"));
                roomDetails.setAdultPrice(resultSet.getDouble("adult_price"));
                roomDetails.setChildPrice(resultSet.getDouble("child_price"));
                roomDetails.setStock(resultSet.getInt("stock"));
                roomDetails.setBedCount(resultSet.getInt("bed_count"));
                roomDetails.setSquareMeters(resultSet.getInt("square_meters"));

                roomDetailsList.add(roomDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomDetailsList;
    }

    public static boolean add(Reservation reservation) {
        String query = "INSERT INTO reservation (room_id, customer_name, customer_surname, customer_identity_number, check_in, check_out, total_price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reservation.getRoomId());
            preparedStatement.setString(2, reservation.getCustomerName());
            preparedStatement.setString(3, reservation.getCustomerSurname());
            preparedStatement.setString(4, reservation.getCustomerIdentityNo());
            preparedStatement.setDate(5, Date.valueOf(reservation.getCheckinDate()));
            preparedStatement.setDate(6, Date.valueOf(reservation.getCheckoutDate()));
            preparedStatement.setDouble(7, reservation.getTotalPrice());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                reservation.setId(rs.getInt("id"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void decreaseRoomStock(int roomId) {
        String query = "UPDATE room SET stock = stock - 1 WHERE id = ?";

        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static double calculatePrice(RoomDetails room, int adults, int children, int nights) {
        double adultPrice = room.getAdultPrice();
        double childPrice = room.getChildPrice();
        return (adults * adultPrice + children * childPrice) * nights;
    }



}
