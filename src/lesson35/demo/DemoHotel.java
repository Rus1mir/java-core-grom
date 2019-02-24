package lesson35.demo;

import lesson35.model.Hotel;
import lesson35.repository.HotelRepository;

public class DemoHotel {
    public static void main(String[] args) throws Exception {
        //saveHotel();
        //getHotelsByName();
        delete(3567874312275730273L);
    }

    private static void saveHotel() throws Exception {
        Hotel hotel = new Hotel(12, "Stolichny", "Ukraine", "Kyiv", "test");
        HotelRepository repository = new HotelRepository();
        System.out.println(repository.addHotel(hotel));
    }

    private static void getHotelsByName() throws Exception {
        HotelRepository repository = new HotelRepository();
        System.out.println(repository.findHotelsByName("Stolichny"));
    }

    private static void getHotelsByCity() throws Exception {
        HotelRepository repository = new HotelRepository();
        System.out.println(repository.findHotelsByCity("Kyiv"));
    }

    private static void delete(long id) throws Exception {
        HotelRepository repository = new HotelRepository();
        repository.deleteHotel(id);
    }
}
