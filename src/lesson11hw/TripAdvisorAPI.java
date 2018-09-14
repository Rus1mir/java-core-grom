package lesson11hw;

public class TripAdvisorAPI implements API {
    private Room[] rooms;

    public TripAdvisorAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    /*
      находит комнаты по заданным параметрам, а так же, количество гостей ищется в диапазоне +-1.
      Например если пользователь ищет комнату с ко-вом гостей 3 и другими параметрами,
      TripAdvisorAPI вернет все комнаты с ко-вом гостей от 2 до четырех
     */
    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {
        Room[] foundRoomsTemp = new Room[rooms.length];
        int foundCount = 0;
        int personsRange = 1;
        for (Room room : rooms) {
            if ((room != null)
                    && (room.getPrice() == price)
                    && (isPersonsNumberInRange(persons, personsRange, room.getPersons()))
                    && (room.getCityName().equals(city))
                    && (room.getHotelFamily().equals(hotel))) {
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

    private boolean isPersonsNumberInRange(int middleNumber, int range, int number) {
        long hiLimit = middleNumber + range;
        long loLimit = (middleNumber - range > 1) ? (middleNumber - range) : 1;
        return (number >= loLimit) && (number <= hiLimit);
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }
}
