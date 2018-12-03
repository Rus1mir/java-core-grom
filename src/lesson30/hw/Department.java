package lesson30.hw;

import java.util.Collection;

public class Department {
    private DepartmentType type;
    private Collection<Employee> employees;

    public Department(DepartmentType type, Collection<Employee> employees) {
        this.type = type;
        this.employees = employees;
    }

    public DepartmentType getType() {
        return type;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }
}
