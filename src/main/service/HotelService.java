package main.service;

import main.helper.Config;
import main.model.hotel.BoardingHouseType;
import main.model.hotel.FacilityFeatures;
import main.model.hotel.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HotelService {

    public static ArrayList<Hotel> listAll(){
        ArrayList<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM hotel";
        Hotel hotel;
        try(Connection connection = Config.connect()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                hotel = new Hotel();
                hotel.setId(resultSet.getInt("id"));
                hotel.setName(resultSet.getString("name"));
                hotel.setAddress(resultSet.getString("address"));
                hotel.setEmail(resultSet.getString("email"));
                hotel.setPhoneNumber(resultSet.getString("phone_number"));
                hotel.setStar(resultSet.getString("star"));

                String boardingTypeStr = resultSet.getString("boarding_house_type");
                if (boardingTypeStr != null){
                    hotel.setBoardingHouseType(BoardingHouseType.valueOf(boardingTypeStr));
                }

                Array sqlArray = resultSet.getArray("facility_features");
                if (sqlArray != null) {
                    String[] featuresArray = (String[]) sqlArray.getArray();
                    List<FacilityFeatures> featuresList = Arrays.stream(featuresArray)
                            .map(FacilityFeatures::valueOf)
                            .collect(Collectors.toList());
                    hotel.setFacilityFeatures(featuresList);
                }

                hotels.add(hotel);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hotels;
    }

}
