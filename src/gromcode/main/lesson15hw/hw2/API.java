package gromcode.main.lesson15hw.hw2;

public interface API {

    /*Интерфейс API который содержит поведение:
      findRooms(int price, int persons, String city, String hotel)
      Room[] getAll()
     */
    Room[] findRooms(int price, int persons, String city, String hotel);

    Room[] getAll();
}

