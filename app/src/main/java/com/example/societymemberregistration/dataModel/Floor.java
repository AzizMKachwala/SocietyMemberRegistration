package com.example.societymemberregistration.dataModel;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    String floorName;
    List<Room> rooms;

    public Floor(String floorName) {
        this.floorName = floorName;
        this.rooms = new ArrayList<>();
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
}
