package lesson35.repository;

import lesson35.model.Hotel;

import java.util.Random;

public class HotelRepository {

    private final String PATH = "D:/HotelDb.txt";

    public Hotel save(Hotel hotel) throws Exception {

        hotel.setId(Math.abs(new Random().nextLong()));
        return (Hotel) DataReaderWriter.save(hotel, PATH);
    }
}
