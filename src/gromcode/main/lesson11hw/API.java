package gromcode.main.lesson11hw;

public interface API {

    /*Интерфейс API который содержит поведение:
      findRooms(int price, int persons, String city, String hotel)
      Room[] getAll()
     */
    Room[] findRooms(int price, int persons, String city, String hotel);

    Room[] getAll();
}
