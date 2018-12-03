package lesson30.hw;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DepartmentDAO {
    private Set<Department> departments;

    public DepartmentDAO(Collection<Department> departments) {
        this.departments = new HashSet<>(departments);
    }

    public Set<Department> getDepartments() {
        return departments;
    }
}
