package lesson35.model;


import lesson35.exception.DataFormatErrorException;

import java.util.Date;
import static lesson35.repository.DataReaderWriter.DATE_FORMAT;

public class Room {
    private long id;
    private int numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

    public Room(long id, int numberOfGuests, double price,
                boolean breakfastIncluded, boolean petsAllowed, Date dateAvailableFrom, Hotel hotel) {

        this.id = id;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
    }

    public Room(String[] fields, Hotel hotel) throws Exception{

        if (fields.length != 7)
            throw new DataFormatErrorException("Can't create object 'Hotel', number of fields id incorrect");

        try {
            this.id = Long.parseLong(fields[0]);
            this.numberOfGuests = Integer.parseInt(fields[1]);
            this.price = Double.parseDouble(fields[2]);
            this.breakfastIncluded = Boolean.parseBoolean(fields[3]);
            this.petsAllowed = Boolean.parseBoolean(fields[4]);
            this.dateAvailableFrom = DATE_FORMAT.parse(fields[5]);
            this.hotel = hotel;
        } catch (Exception e) {
            throw new DataFormatErrorException("Can't create object 'Room', one or many fields is incorrect", e);
        }
    }

    @Override
    public String toString() {

        return String.valueOf(id) + ',' +
                String.valueOf(numberOfGuests) + ',' +
                String.valueOf(price) + ',' +
                String.valueOf(breakfastIncluded) + ',' +
                String.valueOf(petsAllowed) + ',' +
                DATE_FORMAT.format(dateAvailableFrom) + ',' +
                String.valueOf(hotel.getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
