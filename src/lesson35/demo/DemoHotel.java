package lesson35.demo;

import lesson35.model.Hotel;
import lesson35.repository.HotelRepository;

public class DemoHotel {
    public static void main(String[] args) throws Exception {

        Hotel hotel = new Hotel(12, "Stolichny", "Ukraine", "Kyiv", "test");
        HotelRepository repository = new HotelRepository();
        repository.save(hotel);
    }
}
