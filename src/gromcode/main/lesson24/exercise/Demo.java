package gromcode.main.lesson24.exercise;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {

        GeneralDao<Sys> systemDAO = new GeneralDao<>();
        Sys system1 = new Sys(11, "...");

        systemDAO.save(system1);
        System.out.println(Arrays.deepToString(systemDAO.getAll()));

        systemDAO.save(system1);
        System.out.println(Arrays.deepToString(systemDAO.getAll()));

        GeneralDao<Tool> toolDAO = new GeneralDao<>();
        Tool tool1 = new Tool("weew", "dddd");
        toolDAO.save(tool1);

        System.out.println(Arrays.deepToString(toolDAO.getAll()));
    }
}
