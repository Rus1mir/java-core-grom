package lesson35.demo;

import lesson35.model.Hotel;
import lesson35.repository.HotelRepository;

public class DemoHotel {
    public static void main(String[] args) throws Exception {
        //saveHotel();
        getHotelsByName();
    }

    private static void saveHotel() throws Exception {

        Hotel hotel = new Hotel(12, "Stolichny", "Ukraine", "Kyiv", "test");
        HotelRepository repository = new HotelRepository();
        System.out.println(repository.save(hotel));
    }

    private static void getHotelsByName() throws Exception {
        HotelRepository repository = new HotelRepository();
        System.out.println(repository.getHotelByName("Stolichny"));
    }
}
