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
        int foundRoomsCount = 0;
        for (API api : apis) {
            if (api != null) {
                foundRoomsCount += api.findRooms(price, persons, city, hotel).length;
            }
        }
        Room[] foundRooms = new Room[foundRoomsCount];
        foundRoomsCount = 0;
        for (API api : apis) {
            if (api != null) {
                for (Room room : api.findRooms(price, persons, city, hotel)) {
                    foundRooms[foundRoomsCount++] = room;
                }
            }
        }
        return foundRooms;
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
        boolean[] mask = new boolean[api2.getAll().length];
        int count = 0;
        for (int i = 0; i < api1.getAll().length; i++) {
            for (int j = 0; j < api2.getAll().length; j++) {
                if (!mask[j]) {
                    if (isRoomsEqual(api1.getAll()[i], api2.getAll()[j])) {
                        mask[j] = true;
                        count++;
                        break;
                    }
                }
            }
        }
        mask = new boolean[api2.getAll().length];
        Room[] resultRooms = new Room[count];
        for (int i = 0, n = 0; i < api1.getAll().length; i++) {
            for (int j = 0; j < api2.getAll().length; j++) {
                if (!mask[j]) {
                    if (isRoomsEqual(api1.getAll()[i], api2.getAll()[j])) {
                        mask[j] = true;
                        resultRooms[n++] = api1.getAll()[i];
                        break;
                    }
                }
            }
        }
        return resultRooms;
    }

    private boolean isRoomsEqual(Room room1, Room room2) {
        return (room1.getPersons() == room2.getPersons() &&
                room1.getHotelName().equals(room2.getHotelName()) &&
                room1.getCityName().equals(room2.getCityName()) &&
                room1.getPrice() == room2.getPrice());
    }
}
