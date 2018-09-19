package lesson11hw;

public class BookingComAPI implements API {
    private Room[] rooms;

    public BookingComAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    /*находит комнаты по заданным параметрам, а так же комнаты, которые по цене отличаются на 100 единиц в обе стороны.
      Например если пользователь ищет комнату с ценой 50 и другими параметрами,
      BookingComAPI вернет все комнаты с ценой в диапазоне 0 - 150
     */
    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {
        Room[] foundRoomsTemp = new Room[rooms.length];
        int foundCount = 0;
        int priceRange = 100;
        for (Room room : rooms) {
            if ((room != null)
                    && isPriceInRange(price, priceRange, room.getPrice())
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

    private boolean isPriceInRange(int middlePrice, int range, int price) {
        long hiLimit = middlePrice + range;
        long loLimit = (middlePrice - range > 0) ? (middlePrice - range) : 0;
        return (price >= loLimit) && (price <= hiLimit);
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }
}
