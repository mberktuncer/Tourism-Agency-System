package main.model.room;

public class Room {

    private int id;
    private int hotelId;
    private String roomType;
    private int bedCount;
    private int squareMeters;
    private int stock;
    private double adultPrice;
    private double childPrice;

    public Room(int id, int hotelId, String roomType, int bedCount, int squareMeters, int stock, double adultPrice, double childPrice) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomType = roomType;
        this.bedCount = bedCount;
        this.squareMeters = squareMeters;
        this.stock = stock;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
    }

    public Room(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", roomType='" + roomType + '\'' +
                ", bedCount=" + bedCount +
                ", squareMeters=" + squareMeters +
                ", stock=" + stock +
                ", adultPrice=" + adultPrice +
                ", childPrice=" + childPrice +
                '}';
    }
}
