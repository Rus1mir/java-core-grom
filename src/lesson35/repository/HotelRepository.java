package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import lesson35.model.Hotel;

import java.util.ArrayList;
import java.util.Random;

public class HotelRepository extends GeneralRepo<Hotel> {

    public HotelRepository() {
        super.path = "C:/javaExercises/project/HotelDb.txt";
    }

    public ArrayList<Hotel> findHotelsByName(String name) throws Exception {

        String[] filter = new String[]{null, name, null, null, null};
        return getObjectsByFilter(filter);
    }

    public ArrayList<Hotel> findHotelsByCity(String city) throws Exception {

        String[] filter = new String[]{null, null, null, city, null};
        return getObjectsByFilter(filter);
    }

    public Hotel addHotel(Hotel hotel) throws Exception {

        hotel.setId(Math.abs(new Random().nextLong()));
        return save(hotel);
    }

    public void deleteHotel(long hotelId) throws Exception {
        deleteRecordById(hotelId);
    }

    @Override
    protected Hotel createObjFromFields(String[] fields) throws Exception {
        //validateFields(fields);
        try {
            return new Hotel(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], fields[4]);
        } catch (NumberFormatException e) {
            throw new DataFormatErrorException("Wrong Id field format detected");
        }
    }

    @Override
    protected void validateFields(String[] fields) throws Exception {
        for(String f : fields) {
            if (f.trim().equals(""))
                throw new DataFormatErrorException("Empty fields detected");
        }
    }
}