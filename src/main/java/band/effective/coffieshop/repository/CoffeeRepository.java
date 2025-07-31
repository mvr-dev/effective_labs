package band.effective.coffieshop.repository;

import band.effective.coffieshop.model.Coffee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
    @EntityGraph(attributePaths = {"ingredients"})
    @Query("SELECT c FROM Coffee c LEFT JOIN FETCH c.ingredients WHERE c.id = :id")
    Optional<Coffee> findByIdWithIngredients(@Param("id") Long id);

}
