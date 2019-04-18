package lesson35.demo;

import lesson35.controller.RoomController;
import lesson35.controller.UserController;
import lesson35.model.Hotel;
import lesson35.model.Room;


import java.util.Date;

public class DemoRoom {
    public static void main(String[] args) throws Exception{

        UserController userController = new UserController();

        userController.login("Peter", "23423");
        //userController.login("Joann", "1234");

        //findRooms();
        //addRoom();
        deleteRoom(7277790549578145598L);
    }

    private static void addRoom () throws Exception {

        RoomController controller = new RoomController();

        Hotel hotel = new Hotel(9139656224128283922L, "Stolichny", "Ukraine", "Kyiv", "test");
        Room room = new Room(-1, 1, 700, true, true, new Date(), hotel);

        System.out.println(controller.addRoom(room));
    }

    private static void deleteRoom (long id) throws Exception{

        RoomController controller = new RoomController();
        controller.deleteRoom(id);
    }

    private static void findRooms () throws Exception {

        //TODO
    }
}
