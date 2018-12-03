package lesson30.hw;

import lesson30.hw.exceptions.BadRequestException;

import java.util.*;

public class EmployeeDAO {
    private Set<Employee> employees;

    public EmployeeDAO(Collection<Employee> employees) {
        this.employees = new HashSet<>(employees);
    }

    public Collection<Employee> employeesByProject(Project project) {
        List<Employee> res = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getProjects().contains(project))
                res.add(e);
        }
        return res;
    }

    public Collection<Project> projectsByEmployee(Employee employee) throws BadRequestException {

        for (Employee e : employees) {
            if (e.equals(employee))
                return e.getProjects();
        }
        throw new BadRequestException("No employee found: " + employee.getLastName());
    }

    public Collection<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {
        List<Employee> res = new ArrayList<>();

        for (Employee e : employeesWithoutProject()) {
            if (e.getDepartment().getType() == departmentType)
                res.add(e);
        }
        return res;
    }

    public Collection<Employee> employeesWithoutProject() {
        List<Employee> res = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getProjects().isEmpty())
                res.add(e);
        }
        return res;
    }

    public Collection<Employee> employeesByProjectEmployee(Employee employee) {
        Set<Employee> res = new HashSet<>();

        for (Project p : employee.getProjects()) {
            for (Employee e : employees) {
                if (!e.equals(employee) && e.getProjects().contains(p)) {
                    res.add(e);
                }
            }
        }
        return res;
    }

    public Collection<Employee> employeesByTeamLead(Employee lead) throws BadRequestException {

        if (lead.getPosition() != Position.TEAM_LEAD)
            throw new BadRequestException("Requested employee is not a team lead: " + lead.getLastName());

        return employeesByProjectEmployee(lead);
    }

    public Collection<Employee> teamLeadsByEmployee(Employee employee) throws BadRequestException {

        if (employee.getPosition() == Position.TEAM_LEAD)
            throw new BadRequestException("Requested employee is a team lead: " + employee.getLastName());

        List<Employee> res = new ArrayList<>();
        for (Employee e : employeesByProjectEmployee(employee)) {
            if (e.getPosition() == Position.TEAM_LEAD)
                res.add(e);
        }
        return res;
    }
}

