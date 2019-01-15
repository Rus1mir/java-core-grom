package gromcode.main.lesson25.hw;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) throws Exception {
        //testNothingToGet();
        //testSaveNull();
        //testSaveDuplicate();
        //testSaveNoSpace();
        testSaveMultiTypes();
    }

    private static void testNothingToGet() throws Exception {
        GeneralDAO<Tool> generalDAO = new GeneralDAO<>();
        System.out.println(Arrays.deepToString(generalDAO.getAll()));
    }

    private static void testSaveNull() throws Exception {
        GeneralDAO<Tool> generalDAO = new GeneralDAO<>();
        generalDAO.save(null);
        System.out.println(Arrays.deepToString(generalDAO.getAll()));
    }

    private static void testSaveDuplicate() throws Exception {
        GeneralDAO<Sys> generalDAO = new GeneralDAO<>();
        Sys system1 = new Sys(10, "sys01");
        Sys system2 = new Sys(10, "sys02");
        generalDAO.save(system1);
        System.out.println(Arrays.deepToString(generalDAO.getAll()));
        generalDAO.save(system2);
        System.out.println(Arrays.deepToString(generalDAO.getAll()));
    }

    private static void testSaveNoSpace() throws Exception {
        GeneralDAO<Sys> generalDAO = new GeneralDAO<>();

        for (int i = 0; i < 12; i++) {
            Sys sys = new Sys(i, "rrr");
            generalDAO.save(sys);
            System.out.println(Arrays.deepToString(generalDAO.getAll()));
        }
    }

    private static void testSaveMultiTypes() throws Exception {
        GeneralDAO<EntityId> generalDAO = new GeneralDAO<>();

        Tool tool = new Tool(12, "rev");
        Sys sys = new Sys(22, "ttt");
        TestClass testClass = new TestClass(43, 200);

        System.out.println(generalDAO.save(tool).toString());
        System.out.println(generalDAO.save(sys).toString());
        System.out.println(generalDAO.save(testClass).toString());

        System.out.println(Arrays.deepToString(generalDAO.getAll()));
    }
}


