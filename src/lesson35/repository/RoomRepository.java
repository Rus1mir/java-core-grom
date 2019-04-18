package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import lesson35.exception.ReferenceException;
import lesson35.model.Filter;
import lesson35.model.Hotel;
import lesson35.model.Order;
import lesson35.model.Room;

import java.util.ArrayList;


public class RoomRepository extends GeneralRepo<Room> {

    public RoomRepository() {
        super("C:/javaExercises/project/RoomDb.txt", 7);
    }

    public ArrayList<Room> findRoomsByFilter(Filter filter) throws Exception {

        ArrayList<Room> rooms = getObjectsFromDb();
        ArrayList<Room> res = new ArrayList<>();

        for (Room r : rooms) {
            if (filter.equals(r))
                res.add(r);
        }
        return res;
    }

    @Override
    protected Room mapping(String[] fields) throws Exception {

        Hotel hotel = new HotelRepository().getObjectByID(Long.parseLong(fields[6]));

        if (hotel == null) throw new ReferenceException("Hotel with id " + fields[6] + " was no found in HotelDB");

        try {
            return new Room(Long.parseLong(fields[0]),
                    Integer.parseInt(fields[1]),
                    Double.parseDouble(fields[2]),
                    Boolean.parseBoolean(fields[3]),
                    Boolean.parseBoolean(fields[4]),
                    DATE_FORMAT.parse(fields[5]),
                    hotel);
        } catch (NumberFormatException e) {
            throw new DataFormatErrorException("Wrong field format detected");
        }
    }

    @Override
    protected void checkReferences(Room object) throws Exception {

        ArrayList<Order> orders = new OrderRepository().getObjectsFromDb();

        for (Order order : orders) {
            if (order.getRoom().getId() == object.getId())
                throw new ReferenceException("Removing Room " + object.getId() +
                        " was failed cause one of some orders still has reference to it");
        }
    }
}
