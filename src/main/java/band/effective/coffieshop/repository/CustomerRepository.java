package band.effective.coffieshop.repository;

import band.effective.coffieshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
