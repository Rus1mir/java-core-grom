package gromcode.main.lesson8.ads;

import java.util.Date;

public class Ad extends BaseEntity {
    long id;
    int price;
    Date dateCreated;

    public Ad(long id, int price) {
        super(id);
        this.price = price;
        dateCreated = new Date();
    }

    void publishAd() {
    }
}
