package lesson35.service;

import lesson35.model.Hotel;
import lesson35.repository.HotelRepository;

import java.util.ArrayList;

public class HotelService {

    private HotelRepository hotelRepository = new HotelRepository();

    public ArrayList<Hotel> findHotelsByName(String name) throws Exception {

        return hotelRepository.findHotelsByName(name);
    }

    public ArrayList<Hotel> findHotelsByCity(String city) throws Exception {

        return hotelRepository.findHotelsByCity(city);
    }
}
