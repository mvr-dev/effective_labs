package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.dto.CoffeeRequestDTO;
import band.effective.coffeeshop.model.dto.CoffeeResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ICoffeeService {
    Page<CoffeeResponseDTO> getAllCoffees(int pageNumber, int pageSize);

    Optional<CoffeeResponseDTO> getCoffeeById(Long id);
    CoffeeResponseDTO addCoffee(CoffeeRequestDTO coffee);
    CoffeeResponseDTO updateCoffee(Long id, CoffeeRequestDTO coffee);

    void deleteCoffee(Long id);

    List<Coffee> getAllCoffeesById(List<Long> coffeesId);

}
