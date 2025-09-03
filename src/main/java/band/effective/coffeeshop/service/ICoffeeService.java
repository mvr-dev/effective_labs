package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.dto.CoffeeRequestDTO;
import band.effective.coffeeshop.model.dto.CoffeeResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ICoffeeService {
    List<CoffeeResponseDTO> getAllCoffees();
    Optional<CoffeeResponseDTO> getCoffeeById(Long id);
    Coffee addCoffee(CoffeeRequestDTO coffee);
    Coffee updateCoffee(Long id,CoffeeRequestDTO coffee);

    void deleteCoffee(Long id);

    List<CoffeeResponseDTO> getAllCoffeesById(List<Long> coffeesId);

}
