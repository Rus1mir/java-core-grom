package gromcode.main.lesson30.hw;

import java.util.Collection;
import java.util.Date;

public class Firm {
    private Date dateFounded;
    private Collection<Department> departments;
    private Collection<Customer> customers;

    public Firm(Date dateFounded, Collection<Department> departments, Collection<Customer> customers) {
        this.dateFounded = dateFounded;
        this.departments = departments;
        this.customers = customers;
    }
}
