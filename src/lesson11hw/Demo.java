package lesson11hw;

import lesson8.students.CollegeStudent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Room[] rooms = new Room[10];
        rooms[1] = new Room(0, 12, 3, new Date(), "Hilton", "Chicago1");
        rooms[2] = new Room(1, 12, 3, new Date(), "Palace", "Chicago");
        rooms[3] = new Room(2, 13, 5, new Date(), "Hilton", "Chicago");
        rooms[4] = new Room(3, 12, 3, new Date(), "Hilton", "New York");
        rooms[5] = new Room(4, 12, 3, new Date(), "Hilton", "Chicago");
        rooms[6] = new Room(5, 12, 3, new Date(), "Palace", "Chicago");
        rooms[7] = new Room(6, 13, 5, new Date(), "Hilton", "Chicago");
        rooms[8] = new Room(7, 12, 3, new Date(), "Hilton", "New York");
        Room[] rooms1 = new Room[10];
        rooms1[1] = new Room(0, 12, 3, new Date(), "Hilton", "Chicago");
        rooms1[2] = new Room(1, 12, 3, new Date(), "Palace", "Chicago");
        rooms1[3] = new Room(2, 13, 5, new Date(), "Hilton", "Chicago");
        rooms1[4] = new Room(3, 12, 3, new Date(), "Hilton", "New York");
        rooms1[5] = new Room(4, 12, 3, new Date(), "Hilton", "Chicago");
        rooms1[6] = new Room(5, 12, 3, new Date(), "Palace", "Chicago");
        rooms1[7] = new Room(6, 13, 5, new Date(), "Hilton", "Chicago");
        rooms1[8] = new Room(7, 12, 3, new Date(), "Hilton", "New York");
        rooms1[9] = new Room(0, 12, 3, new Date(), "Hilton", "Chicago1");
        Room[] rooms2 = new Room[10];
        rooms2[1] = new Room(0, 12, 3, new Date(), "Hilton", "Chicago");
        rooms2[2] = new Room(1, 12, 3, new Date(), "Palace", "Chicago");
        rooms2[3] = new Room(2, 13, 5, new Date(), "Hilton", "Chicago");
        rooms2[4] = new Room(3, 12, 3, new Date(), "Hilton", "New York");
        rooms2[5] = new Room(4, 12, 3, new Date(), "Hilton", "Chicago");
        rooms2[6] = new Room(5, 12, 3, new Date(), "Palace", "Chicago");
        rooms2[7] = new Room(6, 13, 5, new Date(), "Hilton", "Chicago");
        rooms2[8] = new Room(7, 12, 3, new Date(), "Hilton", "New York");
        API[] apis = new API[3];
        apis[0] = new BookingComApi(rooms);
        apis[1] = new TripAdvisorAPI(rooms1);
        apis[2] = new GoogleAPI(rooms2);
        Controller controller = new Controller(apis);
        //Room[] resultRooms = controller.requestRooms(12, 4, "Chicago", "Hilton");
        //printRooms(resultRooms);
        Room[] result2 = controller.check(apis[0], apis[1]);
        printRooms(result2);
    }

    private static void printRooms(Room[] rooms) {
        for (Room room : rooms) {
            System.out.println(String.valueOf(room.getId()) + "  "
                    + String.valueOf(room.getPersons()) + "  "
                    + String.valueOf(room.getPrice()) + "  "
                    + String.valueOf(room.getHotelFamily()) + "  "
                    + String.valueOf(room.getCityName()));
        }
    }
}
