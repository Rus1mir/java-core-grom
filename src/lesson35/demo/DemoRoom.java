package lesson35.demo;

import lesson35.controller.HotelController;
import lesson35.controller.RoomController;
import lesson35.controller.UserController;
import lesson35.model.Filter;
import lesson35.model.Hotel;
import lesson35.model.Room;
import lesson35.repository.RoomRepository;

import java.util.Date;

public class DemoRoom {

    private static UserController userController = new UserController();
    private static RoomController controller = new RoomController();
    private static HotelController hotelController = new HotelController();

    public static void main(String[] args) throws Exception {

        userController.login("Peter", "23423"); //Admin
        //userController.login("Joann", "1234");

        //addRoom(); //tested
        //deleteRoom(); //tested
        //findRooms(); //tested
    }

    private static void addRoom() throws Exception {

        Hotel hotel = hotelController.findHoteByName("Palace").get(0);
        Room room = new Room(-1, 1, 700, true, true, new Date(), hotel);

        System.out.println(controller.addRoom(room));
    }

    private static void deleteRoom() throws Exception {

        controller.deleteRoom(3402247236796500472L);
    }

    private static void findRooms() throws Exception {

        Filter filter = new Filter(1, 700d, null,
                null, null, "Ukraine", null);

        System.out.println(controller.findRooms(filter));
    }
}
