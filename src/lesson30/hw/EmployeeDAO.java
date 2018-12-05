package lesson30.hw;

import com.sun.xml.internal.bind.v2.TODO;
import lesson30.hw.exceptions.BadRequestException;

import java.util.*;

public class EmployeeDAO {
    private Set<Employee> employees;

    public EmployeeDAO(Collection<Employee> employees) {
        this.employees = new HashSet<>(employees);
    }

    public ArrayList<Employee> employeesByProject(Project project) {
        ArrayList<Employee> res = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getProjects().contains(project))
                res.add(e);
        }
        return res;
    }

    public ArrayList<Project> projectsByEmployee(Employee employee) throws BadRequestException {

        for (Employee e : employees) {
            if (e.equals(employee))
                return e.getProjects();
        }
        throw new BadRequestException("No employee found: " + employee.getLastName());
    }

    public ArrayList<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {
        ArrayList<Employee> res = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getProjects().isEmpty() && e.getDepartment().getType() == departmentType)
                res.add(e);
        }
        return res;
    }

    public ArrayList<Employee> employeesWithoutProject() {
        ArrayList<Employee> res = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getProjects().isEmpty())
                res.add(e);
        }
        return res;
    }

    public ArrayList<Employee> employeesByProjectEmployee(Employee employee) {
        ArrayList<Employee> res = new ArrayList<>();

        for (Employee e : employees) {
            List<Project> temp = new ArrayList<>(employee.getProjects());
            temp.retainAll(e.getProjects());
            if (!temp.isEmpty() && !employee.equals(e))
                res.add(e);
        }
        return res;
    }

    public ArrayList<Employee> employeesByTeamLead(Employee lead) throws BadRequestException {

        if (lead.getPosition() != Position.TEAM_LEAD)
            throw new BadRequestException("Requested employee is not a team lead: " + lead.getLastName());

        return employeesByProjectEmployee(lead);
    }

    public ArrayList<Employee> teamLeadsByEmployee(Employee employee) {
        ArrayList<Employee> res = new ArrayList<>();

        for (Employee e : employees) {
            List<Project> temp = new ArrayList<>(employee.getProjects());
            temp.retainAll(e.getProjects());

            if (!temp.isEmpty() &&
                    e.getPosition() == Position.TEAM_LEAD &&
                    !employee.equals(e))

                res.add(e);
        }
        return res;
    }
}

