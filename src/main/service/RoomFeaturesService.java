package main.service;

import main.helper.Config;
import main.model.room.RoomFeatures;
import main.model.room.RoomFeaturesEnum;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RoomFeaturesService {

    public static boolean add(int roomId, List<RoomFeaturesEnum> features) {
        String query = "INSERT INTO room_features (room_id, features) VALUES (?, ?::room_features_enum[])";

        try (Connection connection = Config.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, roomId);

            if (features != null && !features.isEmpty()) {
                String[] featuresArray = features.stream()
                        .map(Enum::name)
                        .toArray(String[]::new);
                Array sqlArray = connection.createArrayOf("room_features_enum", featuresArray);
                preparedStatement.setArray(2, sqlArray);
            } else {
                preparedStatement.setArray(2, null);
            }
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
