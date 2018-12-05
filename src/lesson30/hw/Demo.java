package lesson30.hw;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

        // Create a "data base" and controller
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Reach", "USA", 10000));
        customers.add(new Customer("Good", "Germany", 12000));

        CustomerDAO customerDAO = new CustomerDAO(customers);

        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project("Cool", customers.get(0)));
        projects.add(new Project("Best", customers.get(0)));
        projects.add(new Project("Complicate", customers.get(1)));
        projects.add(new Project("Business", customers.get(1)));

        ProjectDAO projectDAO = new ProjectDAO(projects);

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Den", "Cooper", sdf.parse("22-11-2016"), Position.DEVELOPER,
                null, new ArrayList<Project>(projects.subList(3, 4))));
        employees.add(new Employee("Chris", "Noe", sdf.parse("25-01-2017"), Position.DEVELOPER,
                null, new ArrayList<Project>(projects.subList(0, 3))));
        employees.add(new Employee("Peter", "Brown", sdf.parse("02-05-2016"), Position.DEVELOPER,
                null, new ArrayList<Project>(projects.subList(0, 3))));
        employees.add(new Employee("Kelly", "Nolan", sdf.parse("10-10-2016"), Position.DEVELOPER,
                null, new ArrayList<Project>(projects.subList(0, 3))));
        employees.add(new Employee("Bruce", "Carpenter", sdf.parse("12-12-2018"), Position.TEAM_LEAD,
                null, new ArrayList<Project>(projects.subList(0, 2))));
        employees.add(new Employee("David", "Lee", sdf.parse("22-10-2017"), Position.DEVELOPER,
                null, new ArrayList<Project>(projects.subList(1, 4))));
        employees.add(new Employee("Sherill", "Jumbo", sdf.parse("20-06-2106"), Position.DEVELOPER,
                null, new ArrayList<Project>(projects.subList(1, 4))));
        employees.add(new Employee("Ivan", "Sokolov", sdf.parse("07-04-2016"), Position.DEVELOPER,
                null, new ArrayList<Project>(projects.subList(1, 4))));
        employees.add(new Employee("Jean", "Van Cool", sdf.parse("22-11-2016"), Position.TEAM_LEAD,
                null, new ArrayList<Project>(projects.subList(2, 4))));
        employees.add(new Employee("Shawn", "Bigger", sdf.parse("15-06-2016"), Position.DEVELOPER,
                null, new ArrayList<Project>(projects.subList(3, 4))));
        employees.add(new Employee("Solomon", "Koan", sdf.parse("13-10-2016"), Position.DESIGNER,
                null, new ArrayList<Project>(projects.subList(1, 3))));
        employees.add(new Employee("John", "Zean", sdf.parse("08-11-2016"), Position.DESIGNER,
                null, new ArrayList<Project>(projects.subList(3, 4))));
        employees.add(new Employee("Alice", "Cool", sdf.parse("09-08-2017"), Position.LEAD_DESIGNER,
                null, new ArrayList<Project>(projects.subList(0, 4))));
        employees.add(new Employee("Ken", "Davide", sdf.parse("26-11-2017"), Position.FINANCE,
                null, new ArrayList<Project>(projects.subList(1, 3))));
        employees.add(new Employee("Zak", "Poll", sdf.parse("20-03-2016"), Position.FINANCE,
                null, new ArrayList<Project>(projects.subList(3, 4))));
        employees.add(new Employee("Ken", "Lucker", sdf.parse("20-03-2016"), Position.ANALYST,
                null, new ArrayList<Project>(projects.subList(1, 2))));
        employees.add(new Employee("Bruce", "Nolan", sdf.parse("20-03-2016"), Position.MANAGER,
                null, new ArrayList<Project>(projects.subList(0, 4))));

        EmployeeDAO employeeDAO = new EmployeeDAO(employees);

        ArrayList<Department> departments = new ArrayList<>();

        departments.add(new Department(DepartmentType.DEVELOPMENT, new ArrayList<>(employees.subList(0, 10))));
        departments.add(new Department(DepartmentType.DESIGN, new ArrayList<>(employees.subList(10, 13))));
        departments.add(new Department(DepartmentType.FINANCE, new ArrayList<>(employees.subList(13, 15))));
        departments.add(new Department(DepartmentType.ANALYTICS, new ArrayList<>(employees.subList(15, 16))));
        departments.add(new Department(DepartmentType.MANAGEMENT, new ArrayList<>(employees.subList(16, 17))));

        for (Department dept : departments) {
            for (Employee e : dept.getEmployees()) {
                e.setDepartment(dept);
            }
        }

        DepartmentDAO departmentDAO = new DepartmentDAO(departments);

        ArrayList<Firm> firms = new ArrayList<>();

        firms.add(new Firm(sdf.parse("10-03-2015"), departments, customers));
        FirmDAO firmDAO = new FirmDAO(firms);

        Controller controller = new Controller(customerDAO, projectDAO, employeeDAO, departmentDAO, firmDAO);

        System.out.println(controller.employeesByProject("Cool"));

        System.out.println(employees.get(8));
        System.out.println(controller.projectsByEmployee(employees.get(8)));

        System.out.println(controller.employeesWithoutProject());

        System.out.println(controller.employeesByDepartmentWithoutProject(DepartmentType.DEVELOPMENT));

        System.out.println(controller.employeesByProjectEmployee(employees.get(8)));

        System.out.println(controller.employeesByTeamLead(employees.get(8)));

        System.out.println(controller.teamLeadsByEmployee(employees.get(0)));

        System.out.println(controller.projectsByCustomer(customers.get(1)));

        System.out.println(controller.employeesByCustomerProjects(customers.get(1)));
    }
}
