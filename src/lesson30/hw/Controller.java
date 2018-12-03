package lesson30.hw;

import lesson30.hw.exceptions.BadRequestException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Controller {
    private CustomerDAO customerDAO;
    private ProjectDAO projectDAO;
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;
    private FirmDAO firmDAO;

    public Controller(CustomerDAO customerDAO, ProjectDAO projectDAO, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO, FirmDAO firmDAO) {
        this.customerDAO = customerDAO;
        this.projectDAO = projectDAO;
        this.employeeDAO = employeeDAO;
        this.departmentDAO = departmentDAO;
        this.firmDAO = firmDAO;
    }

    public Collection<Employee> employeesByProject(String projectName) throws BadRequestException {
        return employeeDAO.employeesByProject(projectDAO.projectByName(projectName));
    }

    public Collection<Project> projectsByEmployee(Employee employee) throws BadRequestException {
        return employeeDAO.projectsByEmployee(employee);
    }

    public Collection<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {
        return employeeDAO.employeesByDepartmentWithoutProject(departmentType);
    }

    public Collection<Employee> employeesWithoutProject() {
        return employeeDAO.employeesWithoutProject();
    }

    public Collection<Employee> employeesByTeamLead(Employee lead) throws BadRequestException {
        return employeeDAO.employeesByTeamLead(lead);
    }

    public Collection<Employee> teamLeadsByEmployee(Employee employee) throws BadRequestException {
        return employeeDAO.teamLeadsByEmployee(employee);
    }

    public Collection<Employee> employeesByProjectEmployee(Employee employee) {
        return employeeDAO.employeesByProjectEmployee(employee);
    }

    public Collection<Project> projectsByCustomer(Customer customer) {
        return projectDAO.projectsByCustomer(customer);
    }

    public Collection<Employee> employeesByCustomerProjects(Customer customer) {
        Set<Employee> res = new HashSet<>();

        for(Project proj : projectDAO.projectsByCustomer(customer)) {
            res.addAll(employeeDAO.employeesByProject(proj));
        }
        return res;
    }

}
