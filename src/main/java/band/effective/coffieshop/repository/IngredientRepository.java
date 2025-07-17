package band.effective.coffieshop.repository;

import band.effective.coffieshop.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    Ingredient getIngredientByName(String name);
}
