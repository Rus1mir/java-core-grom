package lesson35.model;

import lesson35.exception.DataFormatErrorException;

public class Hotel {
    private long id;
    private String name;
    private String country;
    private String city;
    private String street;

    public Hotel(long id, String name, String country, String city, String street) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public Hotel(String[] fields) throws Exception {

        try{
            this.id = Long.parseLong(fields[0]);
            this.name = fields[1];
            this.country = fields[2];
            this.city = fields[3];
            this.street = fields[4];
        }catch (Exception e) {
            throw new DataFormatErrorException("Can't create object 'Hotel', one or many fields is incorrect", e);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(id) + ',' +
                name + ',' +
                country + ',' +
                city + ',' +
                street;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
