package net.proselyte.customerdemo.repository;

import net.proselyte.customerdemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link net.proselyte.customerdemo.model.Customer} class.
 * @author Maxim Eliseev
 * @version 1.0
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
