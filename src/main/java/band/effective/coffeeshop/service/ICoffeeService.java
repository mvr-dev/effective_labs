package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;

import java.util.List;
import java.util.Set;

public interface ICoffeeService {
    List<Coffee> getAllCoffees();
    Coffee getCoffeeById(Long id);
    Coffee addCoffee(Coffee coffee);
    Coffee updateCoffee(Coffee coffee);
    void deleteCoffee(Long id);
    Set<Ingredient> getCoffeeIngredients(Long id);

    List<Coffee> getAllCoffeesById(List<Long> coffeesId);

}
