package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.dto.CoffeeRequestDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ICoffeeService {
    List<Coffee> getAllCoffees();

    Optional<Coffee> getCoffeeById(Long id);
    Coffee addCoffee(CoffeeRequestDTO coffee);
    Coffee updateCoffee(Long id,CoffeeRequestDTO coffee);

    void deleteCoffee(Long id);

    List<Coffee> getAllCoffeesById(List<Long> coffeesId);

}
