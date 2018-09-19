package lesson11hw;

public class GoogleAPI implements API {
    private Room[] rooms;

    public GoogleAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    //ищет сторого по заданным параметрам
    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {
        Room[] foundRoomsTemp = new Room[rooms.length];
        int foundCount = 0;
        int personsRange = 1;
        for (Room room : rooms) {
            if ((room != null)
                    && (room.getPrice() == price)
                    && (room.getPersons() == persons)
                    && (room.getCityName().equals(city))
                    && (room.getHotelName().equals(hotel))) {
                foundRoomsTemp[foundCount] = room;
                foundCount++;
            }
        }
        Room[] foundRooms = new Room[foundCount];
        for (int i = 0; i < foundCount; i++) {
            foundRooms[i] = foundRoomsTemp[i];
        }
        return foundRooms;
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }
}
