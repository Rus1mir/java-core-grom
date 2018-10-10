package lesson15hw1.hw2;
//Controller класс, который содержит поле API[] apis,инициализируется в конструкторе, и два общедоступных метода

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
                    if (api1.getAll()[i].equals(api2.getAll()[j])) {
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
                    if (api1.getAll()[i].equals(api2.getAll()[j])) {
                        mask[j] = true;
                        resultRooms[n++] = api1.getAll()[i];
                        break;
                    }
                }
            }
        }
        return resultRooms;
    }
}


