package lesson35.service;

import lesson35.exception.AccessDeniedExeption;
import lesson35.model.Hotel;
import lesson35.model.User;
import lesson35.repository.HotelRepository;
import lesson35.repository.UserRepository;

import java.util.ArrayList;

public class HotelService {

    private HotelRepository hotelRepository = new HotelRepository();

    public ArrayList<Hotel> findHotelsByName(String name) throws Exception {

        if (!UserRepository.isLogined())
            throw new AccessDeniedExeption("Action not permitted for unknown users, please login");

        return hotelRepository.findHotelsByName(name);
    }

    public ArrayList<Hotel> findHotelsByCity(String city) throws Exception {

        if (!UserRepository.isLogined())
            throw new AccessDeniedExeption("Action not permitted for unknown users, please login");

        return hotelRepository.findHotelsByCity(city);
    }

    public Hotel addHotel(Hotel hotel) throws Exception {

        if (!UserRepository.isAdmin())
            throw new AccessDeniedExeption("Action not permitted for users without admins writes, please login as Admin");

        return hotelRepository.addObjectToDb(hotel);
    }

    public void deleteHotel(long hotelId) throws Exception {

        if (!UserRepository.isAdmin())
            throw new AccessDeniedExeption("Action not permitted for users without admins writes, please login as Admin");

        hotelRepository.deleteObjectById(hotelId);
    }
}
