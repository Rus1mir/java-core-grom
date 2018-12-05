package lesson30.hw;

import lesson30.hw.exceptions.BadRequestException;

import java.util.ArrayList;


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

    public ArrayList<Employee> employeesByProject(String projectName) throws BadRequestException {
        return employeeDAO.employeesByProject(projectDAO.projectByName(projectName));
    }

    public ArrayList<Project> projectsByEmployee(Employee employee) throws BadRequestException {
        return employeeDAO.projectsByEmployee(employee);
    }

    public ArrayList<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {
        return employeeDAO.employeesByDepartmentWithoutProject(departmentType);
    }

    public ArrayList<Employee> employeesWithoutProject() {
        return employeeDAO.employeesWithoutProject();
    }

    public ArrayList<Employee> employeesByTeamLead(Employee lead) throws BadRequestException {
        return employeeDAO.employeesByTeamLead(lead);
    }

    public ArrayList<Employee> teamLeadsByEmployee(Employee employee) {
        return employeeDAO.teamLeadsByEmployee(employee);
    }

    public ArrayList<Employee> employeesByProjectEmployee(Employee employee) {
        return employeeDAO.employeesByProjectEmployee(employee);
    }

    public ArrayList<Project> projectsByCustomer(Customer customer) {
        return projectDAO.projectsByCustomer(customer);
    }

    public ArrayList<Employee> employeesByCustomerProjects(Customer customer) {
        ArrayList<Employee> res = new ArrayList<>();

        for (Project proj : projectDAO.projectsByCustomer(customer)) {
            res.addAll(employeeDAO.employeesByProject(proj));
        }
        return res;
    }

}
