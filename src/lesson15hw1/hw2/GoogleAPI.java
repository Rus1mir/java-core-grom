package lesson15hw1.hw2;

import java.util.Date;

public class GoogleAPI implements API {
    private Room[] rooms;

    public GoogleAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    //ищет сторого по заданным параметрам
    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {
        Room sampleRoom = new Room(-1, price, persons, new Date(), hotel, city);
        int foundCount = 0;
        for (Room room : rooms) {
            if (sampleRoom.equals(room)) {
                foundCount++;
            }
        }
        Room[] foundRooms = new Room[foundCount];
        foundCount = 0;
        for (Room room : rooms) {
            if (sampleRoom.equals(room)) {
                foundRooms[foundCount] = room;
                foundCount++;
            }
        }
        return foundRooms;
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }
}
