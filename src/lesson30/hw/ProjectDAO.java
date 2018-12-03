package lesson30.hw;

import lesson30.hw.exceptions.BadRequestException;

import java.util.*;

public class ProjectDAO {

    private Set<Project> projects;

    public ProjectDAO(Collection<Project> projects) {
        this.projects = new HashSet<>(projects);
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public Collection<Project> projectsByCustomer(Customer customer) {
        List<Project> res = new ArrayList<>();

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
