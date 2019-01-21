package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import lesson35.model.Hotel;

import java.util.ArrayList;
import java.util.Random;

public class HotelRepository {

    private final String PATH = "D:/HotelDb.txt";

    public Hotel save(Hotel hotel) throws Exception {

        hotel.setId(Math.abs(new Random().nextLong()));
        return (Hotel) DataReaderWriter.save(hotel, PATH);
    }

    public ArrayList<Hotel> getHotelByName(String name) throws Exception {

        ArrayList<Hotel> res = new ArrayList<Hotel>();

        for (String rec : DataReaderWriter.getRecords(PATH)) {
            String[] fields = rec.split(",");
            if (name.equals(fields[1])) {
                res.add(getHotelFromFields(fields));
            }
        }
        return res;
    }

    private Hotel getHotelFromFields(String[] fields) throws Exception {
        long id = 0;
        try{
            id = Long.parseLong(fields[0]);
        }catch (Exception e) {
            new DataFormatErrorException("Can't create object 'Hotel' cause wrong id field");
        }

        return new Hotel(id, fields[1], fields[2], fields[3], fields[4]);
    }
}
