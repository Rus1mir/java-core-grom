package lesson35.repository;

import lesson35.model.Room;

import java.util.Random;

public class RoomRepository {
    private final String PATH = "D:/RoomDb.txt";

    public Room save(Room room) throws Exception {

        room.setId(Math.abs(new Random().nextLong()));
        return (Room) DataReaderWriter.save(room, PATH);
    }
}
