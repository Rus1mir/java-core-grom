package lesson35.model;

import java.util.Date;
import java.util.Objects;

public class Filter {
    private Integer numberOfGuests;
    private Double price;
    private Boolean breakfastIncluded;
    private Boolean petsAllowed;
    private Date dateAvailableFrom;
    private String country;
    private String city;

    public Filter(Integer numberOfGuests, Double price, Boolean breakfastIncluded,
                  Boolean petsAllowed, Date dateAvailableFrom, String country, String city) {

        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.country = country;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o != null || o.getClass() == Room.class) return roomEquals((Room) o);
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return Objects.equals(numberOfGuests, filter.numberOfGuests) &&
                Objects.equals(price, filter.price) &&
                Objects.equals(breakfastIncluded, filter.breakfastIncluded) &&
                Objects.equals(petsAllowed, filter.petsAllowed) &&
                Objects.equals(dateAvailableFrom, filter.dateAvailableFrom) &&
                Objects.equals(country, filter.country) &&
                Objects.equals(city, filter.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfGuests, price, breakfastIncluded, petsAllowed, dateAvailableFrom, country, city);
    }

    private boolean roomEquals (Room room) {

        return  numberOfGuests == null || numberOfGuests == room.getNumberOfGuests() &&
                price == null || price == room.getPrice() &&
                breakfastIncluded == null || breakfastIncluded == room.isBreakfastIncluded() &&
                petsAllowed == null || petsAllowed == room.isPetsAllowed() &&
                dateAvailableFrom == null || dateAvailableFrom.after(room.getDateAvailableFrom()) &&
                country == null || country.equals(room.getHotel().getCountry()) &&
                city == null || city.equals(room.getHotel().getCity());
    }
}