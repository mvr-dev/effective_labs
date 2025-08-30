package band.effective.coffeeshop.repository;

import band.effective.coffeeshop.model.CustomerOrder;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<CustomerOrder,Long> {
    @EntityGraph(attributePaths = {"coffees"})
    @Query("SELECT c FROM CustomerOrder c LEFT JOIN FETCH c.coffees WHERE c.id = :id")
    Optional<CustomerOrder> findByIdWithCoffees(@Param("id") Long id);
}
