package lesson35.demo;

import lesson35.model.Hotel;
import lesson35.model.Order;
import lesson35.model.Room;
import lesson35.model.User;
import lesson35.repository.OrderRepository;

import java.util.Date;

public class DemoOrder {
    public static void main(String[] args) throws Exception {

        Hotel hotel = new Hotel(12, "Stolichny", "Ukraine", "Kyiv", "test");
        Room room = new Room(-1, 3, 333, false, false, new Date(), hotel);
        User user = new User(-1, "fff", "ffff", "ff", User.UserType.USER);

        Order order = new Order(-1, user, room, new Date(), new Date(), 335);
        OrderRepository repository = new OrderRepository();
        repository.save(order);
    }
}
