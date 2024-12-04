package main.model.room;

public class RoomFeatures {
    private int id;
    private int roomId;
    private String roomFeatures;
    private boolean isAvailable;

    public RoomFeatures(int id, int roomId, String roomFeatures, boolean isAvailable) {
        this.id = id;
        this.roomId = roomId;
        this.roomFeatures = roomFeatures;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomFeatures() {
        return roomFeatures;
    }

    public void setRoomFeatures(String roomFeatures) {
        this.roomFeatures = roomFeatures;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
