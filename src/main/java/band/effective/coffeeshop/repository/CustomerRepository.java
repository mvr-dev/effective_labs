package band.effective.coffeeshop.repository;

import band.effective.coffeeshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
