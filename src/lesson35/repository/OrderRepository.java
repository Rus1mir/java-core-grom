package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import lesson35.exception.ReferenceException;
import lesson35.model.Order;
import lesson35.model.Room;
import lesson35.model.User;

public class OrderRepository extends GeneralRepo<Order> {

    public OrderRepository() {
        super("C:/javaExercises/project/OrderDb.txt", 6);
    }

    @Override
    protected Order mapping(String[] fields) throws Exception {

        User user = new UserRepository().getObjectByID(Long.parseLong(fields[1]));

        if (user == null) throw new ReferenceException("User with id " + fields[1] + " was no found in UserDB");

        Room room = new RoomRepository().getObjectByID(Long.parseLong(fields[2]));

        if (room == null) throw new ReferenceException("Room with id " + fields[2] + " was no found in RoomDB");

        try {
            return new Order(Long.parseLong(fields[0]),
                    user,
                    room,
                    DATE_FORMAT.parse(fields[3]),
                    DATE_FORMAT.parse(fields[4]),
                    Double.parseDouble(fields[5]));

        } catch (NumberFormatException e) {
            throw new DataFormatErrorException("Wrong Id field format detected");
        }
    }

    protected void checkReferences(Order object) {
    }
}
