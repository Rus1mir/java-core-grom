package lesson35.controller;

import lesson35.model.Hotel;
import lesson35.service.HotelService;

import java.util.ArrayList;

public class HotelController {

    private HotelService hotelService = new HotelService();

    public ArrayList<Hotel> findHoteByName(String name) throws Exception {

        return hotelService.findHotelsByName(name);
    }

    public ArrayList<Hotel> findHotelByCity(String city) throws Exception {

        return hotelService.findHotelsByCity(city);
    }

}
