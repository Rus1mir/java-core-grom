package lesson30.hw;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomerDAO {
    private Set<Customer> customers;

    public CustomerDAO(Collection<Customer> customers) {
        this.customers = new HashSet<>(customers);
    }

    public Set<Customer> getCustomers() {
        return customers;
    }
}
