package lesson11hw;
//Controller класс, который содержит поле API[] apis,инициализируется в конструкторе, и два общедоступных метода

import java.util.Arrays;

public class Controller {
    API[] apis;

    public Controller(API[] apis) {
        this.apis = apis;
    }

    /*Room[] requestRooms(int price, int persons, String city, String hotel)
     Который используя все реализации интерфейса API, находит комнаты по заданным параметрам
    */
    public Room[] requestRooms(int price, int persons, String city, String hotel) {
        Room[] foundRooms = new Room[0];
        for (API api : apis) {
            if (api != null) {
                foundRooms = concat(foundRooms, getRoomsFromApi(api, price, persons, city, hotel));
            }
        }
        return foundRooms;
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    private Room[] getRoomsFromApi(API api, int price, int persons, String city, String hotel) {
        return api.findRooms(price, persons, city, hotel);
    }

    /*
     Room[] check(API api1, API api2)
     Который находит общие комнаты у двух API (в результирующий массив добавлять комнаты с api1).
     Комнаты будем считать равными, если у них одинаковые все поля кроме id и dateAvailableFrom.
     Гарантируется что id комнаты уникальный во всей системе
    */
    public Room[] check(API api1, API api2) {
        Room[] rooms1 = api1.getAll();
        Room[] rooms2 = api2.getAll();
        Room[] resultRoomsTemp = new Room[rooms1.length + rooms2.length];
        int index = 0;
        for (int i = 0; i < rooms1.length; i++) {
            boolean firstEntry = true;
            for (int j = 0; j < rooms2.length; j++) {
                if (isRoomsLike(rooms1[i], rooms2[j])) {
                    if (firstEntry) {
                        resultRoomsTemp[index] = rooms1[i];
                        firstEntry = false;
                        index++;
                    }
                    rooms2[j] = null;
                }
            }
        }
        Room[] resultRooms = new Room[index];
        index = 0;
        for (Room room : resultRoomsTemp) {
            if (room != null) {
                resultRooms[index] = room;
                index++;
            }
        }
        return resultRooms;
    }

    private boolean isRoomsLike(Room room1, Room room2) {
        if ((room1 == null) || (room2 == null)) return false;
        return ((room1.getPersons() == room2.getPersons()) &&
                room1.getHotelFamily().equals(room2.getHotelFamily()) &&
                room1.getCityName().equals(room2.getCityName()) &&
                (room1.getPrice() == room2.getPrice()));
    }
}
