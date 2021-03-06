package lesson35.model;

public class Hotel extends Entity {
    private String name;
    private String country;
    private String city;
    private String street;

    public Hotel(long id, String name, String country, String city, String street) {
        super(id);
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + ',' +
                name + ',' +
                country + ',' +
                city + ',' +
                street;
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
