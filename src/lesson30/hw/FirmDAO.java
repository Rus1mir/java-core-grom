package lesson30.hw;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FirmDAO {
    private Set<Firm> firms;

    public FirmDAO(Collection<Firm> firms) {
        this.firms = new HashSet<>(firms);
    }

    public Set<Firm> getFirms() {
        return firms;
    }
}
