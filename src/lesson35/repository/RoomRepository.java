package lesson35.repository;

import lesson35.model.Filter;
import lesson35.model.Hotel;
import lesson35.model.Room;

import java.util.ArrayList;
import java.util.Random;

import static lesson35.repository.DataReaderWriter.DATE_FORMAT;

public class RoomRepository {

    private final String PATH = "D:/RoomDb.txt";
    private final String HOTEL_PATH = "D:/HotelDb.txt";

    public Room addRoom(Room room) throws Exception {

        room.setId(Math.abs(new Random().nextLong()));
        return DataReaderWriter.save(room, PATH);
    }

    public void deleteRoom(long id) throws Exception {
        DataReaderWriter.deleteRecordById(PATH, id);
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

        ArrayList<String[]> preFilteredRoomRec = DataReaderWriter.getRecordsByFilter(PATH, roomFilter);
        ArrayList<String[]> preFilteredHotelRec = DataReaderWriter.getRecordsByFilter(PATH, hotelFilter);

        for (String[] room : preFilteredRoomRec) {
            for(String[] hotel : preFilteredHotelRec){
                if (room[6].equals(hotel[0]))
                    res.add(new Room(room, new Hotel(hotel)));
            }
        }
        return res;
    }
}
