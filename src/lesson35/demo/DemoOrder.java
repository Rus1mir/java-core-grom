package lesson35.demo;

import lesson35.controller.*;
import lesson35.model.*;
import lesson35.repository.HotelRepository;


public class DemoOrder {

    private static UserController userController = new UserController();
    private static RoomController roomController = new RoomController();
    private static OrderController controller = new OrderController();

    public static void main(String[] args) throws Exception {

        userController.login("Joann", "1234");

        //bookRoom(); //tested

        cancelReservation(); //tested
    }

    private static void bookRoom() throws Exception {

        Room room = roomController.findRooms(new Filter(1, 700d, null,
                null, null, "Ukraine", null)).get(0);

        System.out.println("found room: - " + room + "hotel: - " + new HotelRepository().getObjectByID(room.getHotel().getId()));

        controller.bookRoom(room.getId(), 6000027392535818329L, room.getHotel().getId());
    }

    private static void cancelReservation() throws Exception {

        controller.cancelReservation(1543527144224343647L, 6000027392535818329L);
    }
}
