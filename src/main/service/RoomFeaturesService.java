package main.service;

import main.helper.Config;
import main.model.room.RoomFeatures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomFeaturesService {

    public static boolean add(RoomFeatures roomFeatures){
        String query = "INSERT INTO room_features (room_id, feature_type, is_available) " +
                "VALUES (?, ?, ?)";

        try(Connection connection = Config.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, roomFeatures.getId());
            preparedStatement.setString(2, roomFeatures.getRoomFeatures());
            preparedStatement.setBoolean(3, roomFeatures.isAvailable());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
