package band.effective.coffieshop.service;

import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.model.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICoffeeService {
    List<Coffee> getAllCoffees();
    Coffee getCoffeeById(Long id);
    Coffee addCoffee(Coffee coffee);
    Coffee updateCoffee(Coffee coffee);
    void deleteCoffee(Long id);
    Set<Ingredient> getCoffeeIngredients(Long id);
}
