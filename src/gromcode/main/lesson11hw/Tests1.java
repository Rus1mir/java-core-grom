package gromcode.main.lesson11hw;

import java.util.Date;

public class Tests1 {
    public static void main(String[] args) {
        //isPriceInRangeTest();
        //isConditionMatchOrNullTest();
        //isRoomsEqualsTest();
    }

    private static void isPriceInRangeTest() {
        for (int i = -200; i < 200; i++) {
            System.out.print(i);
            System.out.print(isPriceInRange(50, 100, i));
            System.out.println();
        }
    }

    private static boolean isPriceInRange(int middlePrice, int range, int price) {
        long hiLimit = middlePrice + range;
        long loLimit = (middlePrice - range > 0) ? (middlePrice - range) : 0;
        return (price >= loLimit) && (price <= hiLimit);
    }

    //----------------------------------------------------------------------
    private static void isConditionMatchOrNullTest() {
        String[] strings = {null, "", "Вася", "Петя", "Вася", "Иван"};
        for (String s1 : strings) {
            for (String s2 : strings) {
                System.out.print(s1 + "  " + s2 + " ");
                System.out.print(isConditionMatchOrNull(s1, s2));
                System.out.println();
            }
        }
    }

    private static boolean isConditionMatchOrNull(String string1, String string2) {
        if ((string1 == null) || (string2 == null)) return true;
        return (string1.equals(string2));
    }

    //-----------------------------------------------------------------------
    private static void isRoomsEqualsTest() {
        Room[] rooms = new Room[10];
        rooms[1] = new Room(0, 12, 3, new Date(), "Hilton", "Chicago");
        rooms[2] = new Room(1, 12, 3, new Date(), "Palace", "Chicago");
        rooms[3] = new Room(2, 13, 5, new Date(), "Hilton", "Chicago");
        rooms[4] = new Room(3, 12, 3, new Date(), "Hilton", "New York");
        rooms[5] = new Room(4, 12, 3, new Date(), "Hilton", "Chicago");
        rooms[6] = new Room(5, 12, 3, new Date(), "Palace", "Chicago");
        rooms[7] = new Room(6, 13, 5, new Date(), "Hilton", "Chicago");
        rooms[8] = new Room(7, 12, 3, new Date(), "Hilton", "New York");

        for (Room room1 : rooms) {
            for (Room room2 : rooms) {
                System.out.print(idOrNullString(room1) + "  " + idOrNullString(room2) + "  ");
                System.out.print(isRoomsEquals(room1, room2));
                System.out.println();
            }
        }
    }

    private static String idOrNullString(Room room) {
        String result = (room == null) ? "null" : Long.toString(room.getId());
        return result;
    }

    private static boolean isRoomsEquals(Room room1, Room room2) {
        if ((room1 == null) || (room2 == null)) return false;
        return ((room1.getPersons() == room2.getPersons()) &&
                room1.getHotelName().equals(room2.getHotelName()) &&
                room1.getCityName().equals(room2.getCityName()) &&
                (room1.getPrice() == room2.getPrice()));
    }
}
