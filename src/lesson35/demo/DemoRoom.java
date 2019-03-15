package lesson35.demo;

import lesson35.model.Hotel;
import lesson35.model.Room;
import lesson35.repository.RoomRepository;

import java.util.Date;

public class DemoRoom {
    public static void main(String[] args) throws Exception{
        deleteRoom(234139014461112995L);
    }

    private static void addRoom () throws Exception {

        Hotel hotel = new Hotel(12, "Stolichny", "Ukraine", "Kyiv", "test");
        Room room = new Room(-1, 3, 333, false, false, new Date(), hotel);
        RoomRepository repository = new RoomRepository();
        System.out.println(repository.addRoom(room));
    }

    private static void deleteRoom (long id) throws Exception{
        RoomRepository repository = new RoomRepository();
        repository.deleteRoom(id);
    }
}