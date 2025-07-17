package band.effective.coffieshop.service.impl;

import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.model.Ingredient;
import band.effective.coffieshop.repository.CoffeeRepository;
import band.effective.coffieshop.service.ICoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoffeeService implements ICoffeeService {
    private final CoffeeRepository repository;
    @Override
    public List<Coffee> getAllCoffees() {
        return repository.findAll();
    }

    @Override
    public Coffee getCoffeeById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Coffee addCoffee(Coffee coffee) {
        return repository.save(coffee);
    }

    @Override
    public Coffee updateCoffee(Coffee coffee) {
        return repository.save(coffee);
    }

    @Override
    public void deleteCoffee(Long id) {
        repository.delete(repository.getReferenceById(id));
    }

    @Override
    public Set<Ingredient> getCoffeeIngredients(Long id) {
        return repository.getReferenceById(id).getIngredients();
    }
}
