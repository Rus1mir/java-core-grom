package gromcode.main.lesson30.hw;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Department {
    private DepartmentType type;
    private Set<Employee> employees;

    public Department(DepartmentType type, Collection<Employee> employees) {
        this.type = type;
        this.employees = new HashSet<Employee>(employees);
    }

    public DepartmentType getType() {
        return type;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }
}
