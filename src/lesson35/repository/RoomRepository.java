package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import lesson35.model.Filter;
import lesson35.model.Hotel;
import lesson35.model.Room;
import java.util.ArrayList;
import java.util.Random;



public class RoomRepository extends GeneralRepo<Room> {

    private HotelRepository hotelRepo = new HotelRepository();

    public RoomRepository() {
        super.path = "C:/javaExercises/project/RoomDb.txt";
    }

    public Room addRoom(Room room) throws Exception {

        room.setId(Math.abs(new Random().nextLong()));
        return save(room);
    }

    public void deleteRoom(long id) throws Exception {
        deleteRecordById(id);
    }

    public ArrayList<Room> findRoomsByFilter(Filter filter) throws Exception {

        ArrayList<Room> res = new ArrayList<>();

        String[] roomFilter = {null,
                filter.getNumberOfGuests().toString(),
                filter.getPrice().toString(),
                filter.getBreakfastIncluded().toString(),
                filter.getPetsAllowed().toString(),
                filter.getDateAvailableFrom().toString(),
                null};

        String[] hotelFilter = {null,
                null,
                filter.getCountry(),
                filter.getCity(),
                null};

        ArrayList<Room> preFilteredRooms = getObjectsByFilter(roomFilter);
        ArrayList<Hotel> preFilteredHotels = hotelRepo.getObjectsByFilter(hotelFilter);

        for (Room room : preFilteredRooms) {
            for (Hotel hotel : preFilteredHotels) {
                if (room.getHotel().getId() == hotel.getId())
                    res.add(room);
            }
        }

        return res;
    }

    @Override
    protected Room createObjFromFields(String[] fields) throws Exception {
        //validateFields(fields);
        try {
            return new Room(Long.parseLong(fields[0]),
                    Integer.parseInt(fields[1]),
                    Double.parseDouble(fields[2]),
                    Boolean.parseBoolean(fields[3]),
                    Boolean.parseBoolean(fields[4]),
                    DATE_FORMAT.parse(fields[5]),
                    hotelRepo.getObjectById(Long.parseLong(fields[6])));
        } catch (NumberFormatException e) {
            throw new DataFormatErrorException("Wrong field format detected");
        }
    }

    @Override
    protected void validateFields(String[] fields) throws Exception {

    }
}
