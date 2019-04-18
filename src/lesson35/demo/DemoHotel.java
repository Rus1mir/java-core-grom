package lesson35.demo;

import lesson35.controller.HotelController;
import lesson35.controller.UserController;
import lesson35.model.Hotel;
import lesson35.repository.HotelRepository;

public class DemoHotel {
    public static void main(String[] args) throws Exception {

        UserController userController = new UserController();

        userController.login("Peter", "23423");
        //userController.login("Joann", "1234");

        //findByName();
        //findByCity();
        //addHotel();
        delete(6563717573475032907L);

    }

    private static void findByCity () throws Exception {
        HotelController controller = new HotelController();
        System.out.println(controller.findHotelByCity("Kyiv"));
    }

    private static void findByName () throws Exception {
        HotelController controller = new HotelController();
        System.out.println(controller.findHoteByName("Hilton"));
    }

    private static void addHotel() throws Exception {
        Hotel hotel = new Hotel(12, "Gazda", "Ukraine", "Kyiv", "Solomenskaya");
        HotelController controller = new HotelController();
        System.out.println(controller.addHotel(hotel));
    }

    private static void delete(long id) throws Exception {
        HotelController controller = new HotelController();
        controller.deleteHotel(id);
    }
}
