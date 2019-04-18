package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import lesson35.exception.ReferenceException;
import lesson35.model.Hotel;
import lesson35.model.Room;

import java.util.ArrayList;

public class HotelRepository extends GeneralRepo<Hotel> {

    public HotelRepository() {

        super("C:/javaExercises/project/HotelDb.txt", 5);
    }

    public ArrayList<Hotel> findHotelsByName(String name) throws Exception {

        ArrayList<Hotel> res = new ArrayList<>();

        for(Hotel h : getObjectsFromDb()){

            if (h.getName().equals(name))
                res.add(h);
        }
        return res;
    }

    public ArrayList<Hotel> findHotelsByCity(String city) throws Exception {

        ArrayList<Hotel> res = new ArrayList<>();

        for(Hotel h : getObjectsFromDb()){

            if (h.getCity().equals(city))
                res.add(h);
        }
        return res;
    }

    @Override
    protected Hotel mapping(String[] fields) throws Exception {

        try {
            return new Hotel(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], fields[4]);
        } catch (NumberFormatException e) {
            throw new DataFormatErrorException("Wrong Id field format detected");
        }
    }

    @Override
    protected void checkReferences(Hotel object) throws Exception {

        ArrayList<Room> rooms = new RoomRepository().getObjectsFromDb();

        for (Room room : rooms) {
            if (room.getHotel().getId() == object.getId())
                throw new ReferenceException("Removing Hotel " + object.getId() +
                        " was failed cause one of some rooms still has reference to it");
        }
        //Maybe delete this rooms looks better, but now exception only
    }
}