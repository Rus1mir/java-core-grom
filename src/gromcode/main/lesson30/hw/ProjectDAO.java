package gromcode.main.lesson30.hw;

import gromcode.main.lesson30.hw.exceptions.BadRequestException;

import java.util.*;

public class ProjectDAO {

    private HashSet<Project> projects;

    public ProjectDAO(Collection<Project> projects) {
        this.projects = new HashSet<>(projects);
    }

    public HashSet<Project> getProjects() {
        return projects;
    }

    public ArrayList<Project> projectsByCustomer(Customer customer) {
        ArrayList<Project> res = new ArrayList<>();

        for (Project p : projects) {
            if (p.getCustomer().equals(customer))
                res.add(p);
        }
        return res;
    }

    public Project projectByName(String projectName) throws BadRequestException {

        for (Project p : projects) {
            if (p.getName().equals(projectName))
                return p;
        }
        throw new BadRequestException("Project not found name: " + projectName);
    }


}
