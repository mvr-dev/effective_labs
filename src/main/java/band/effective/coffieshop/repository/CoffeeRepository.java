package band.effective.coffieshop.repository;

import band.effective.coffieshop.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {

}
