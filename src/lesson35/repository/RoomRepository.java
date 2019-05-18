package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import lesson35.exception.ReferenceException;
import lesson35.model.*;

import java.util.ArrayList;
import java.util.Date;

public class RoomRepository extends GeneralRepo<Room> {

    public RoomRepository() {
        super("C:/javaExercises/project/RoomDb.txt", 7);
    }

    public ArrayList<Room> findRoomsByFilter(Filter filter) throws Exception {

        ArrayList<Room> rooms = getAllObjectsFromDb();
        ArrayList<Room> res = new ArrayList<>();

        for (Room r : rooms) {
            if (isMatchFilter(filter, r))
                res.add(r);
        }
        return res;
    }

    public void changeAvailableDate(long id, Date newDate) throws Exception {

        Room room = getObjectByID(id);
        room.setDateAvailableFrom(newDate);
        deleteById(id);
        addObjectToDb(room);
    }

    //Вынес иквелс сюда по замечанию
    private boolean isMatchFilter(Filter filter, Room room) {

        return (filter.getNumberOfGuests() == null || filter.getNumberOfGuests() == room.getNumberOfGuests()) &&
                (filter.getPrice() == null || filter.getPrice() == room.getPrice()) &&
                (filter.getBreakfastIncluded() == null || filter.getBreakfastIncluded() == room.isBreakfastIncluded()) &&
                (filter.getPetsAllowed() == null || filter.getPetsAllowed() == room.isPetsAllowed()) &&
                (filter.getDateAvailableFrom() == null || filter.getDateAvailableFrom().after(room.getDateAvailableFrom())) &&
                (filter.getCountry() == null || filter.getCountry().equals(room.getHotel().getCountry())) &&
                (filter.getCity() == null || filter.getCity().equals(room.getHotel().getCity()));
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
    public void deleteObjectById(long id) throws Exception {

        ArrayList<Order> orders = new OrderRepository().getAllObjectsFromDb();

        for (Order order : orders) {
            if (order.getRoom().getId() == id)
                throw new ReferenceException("Removing Room " + id +
                        " was failed cause one of some orders still has reference to it");
        }
        deleteById(id);
    }
}
