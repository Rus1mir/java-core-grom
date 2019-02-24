package lesson35.repository;

import lesson35.model.Hotel;

import java.util.ArrayList;
import java.util.Random;

public class HotelRepository {

    private final String PATH = "D:/HotelDb.txt";

    public ArrayList<Hotel> findHotelsByName(String name) throws Exception {

        String[] filter = new String[]{null, name, null, null, null};
        return getFromRecordset(DataReaderWriter.getRecordsByFilter(PATH, filter));
    }

    public ArrayList<Hotel> findHotelsByCity(String city) throws Exception {

        String[] filter = new String[]{null, null, null, city, null};
        return getFromRecordset(DataReaderWriter.getRecordsByFilter(PATH, filter));
    }

    public Hotel addHotel(Hotel hotel) throws Exception {

        hotel.setId(Math.abs(new Random().nextLong()));
        return DataReaderWriter.save(hotel, PATH);
    }

    public void deleteHotel(long hotelId) throws Exception {
        DataReaderWriter.deleteRecordById(PATH, hotelId);
    }

    private ArrayList<Hotel> getFromRecordset(ArrayList<String[]> recordset) throws Exception {

        ArrayList<Hotel> res = new ArrayList<>();

        for (String[] rec : recordset) {
            res.add(new Hotel(rec));
        }
        return res;
    }
}