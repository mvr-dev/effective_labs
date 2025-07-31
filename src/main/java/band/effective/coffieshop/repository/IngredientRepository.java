package band.effective.coffieshop.repository;

import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.model.Ingredient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    Ingredient getIngredientByName(String name);
    @EntityGraph(attributePaths = {"coffeesWith"})
    @Query("SELECT c FROM Ingredient  c LEFT JOIN FETCH c.coffeesWith WHERE c.id = :id")
    Optional<Ingredient> findByIdWithCoffees(@Param("id") Long id);
}
