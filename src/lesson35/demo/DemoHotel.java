package lesson35.demo;

import lesson35.controller.HotelController;
import lesson35.controller.UserController;
import lesson35.model.Hotel;
import lesson35.repository.HotelRepository;

public class DemoHotel {
    private static UserController userController = new UserController();
    private static HotelController controller = new HotelController();

    public static void main(String[] args) throws Exception {

        userController.login("Peter", "23423"); //Admin role
        //userController.login("Joann", "1234"); //User role

        //findByName(); //tested
        //findByCity(); //tested
        //addHotel(); //tested
        delete(); //tested

    }

    private static void findByCity () throws Exception {

        System.out.println(controller.findHotelByCity("Kyiv"));
    }

    private static void findByName () throws Exception {

        System.out.println(controller.findHoteByName("Hilton"));
    }

    private static void addHotel() throws Exception {
        Hotel hotel = new Hotel(-1, "Sheraton", "Ukraine", "Kyiv", "Main");
        System.out.println(controller.addHotel(hotel));
    }

    private static void delete() throws Exception {

        controller.deleteHotel(controller.findHoteByName("Sheraton").get(0).getId());
    }
}
