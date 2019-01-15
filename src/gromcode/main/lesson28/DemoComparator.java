package gromcode.main.lesson28;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DemoComparator {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

        Capability capability1 = new Capability(1001, "a", "a", true, sdf.parse("03-10-2018"));
        Capability capability2 = new Capability(1005, "a", "b", false, sdf.parse("05-11-2016"));
        Capability capability3 = new Capability(900, "c", "rrr", true, sdf.parse("12-10-2014"));
        Capability capability4 = new Capability(900, "d", "rrr", false, sdf.parse("03-10-2017"));
        ArrayList<Capability> capabilities = new ArrayList<>();
        capabilities.add(capability1);
        capabilities.add(capability2);
        capabilities.add(capability3);
        capabilities.add(capability4);

        System.out.println(capabilities);
        capabilities.sort(new IsActiveComparator());
        System.out.println(capabilities);

        capabilities.sort(new DateComparator());
        System.out.println(capabilities);

        capabilities.sort(new FullComparator());
        System.out.println(capabilities);
    }
}
