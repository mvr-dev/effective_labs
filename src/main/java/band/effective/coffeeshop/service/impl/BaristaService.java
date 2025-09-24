package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Barista;
import band.effective.coffeeshop.repository.BaristaRepository;
import band.effective.coffeeshop.service.IBaristaService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Primary
@AllArgsConstructor
public class BaristaService implements IBaristaService {
    private final BaristaRepository repository;
    @Override
    public List<Barista> getAllBaristas() {
        return repository.findAll();
    }

    @Override
    public Barista getBaristaById(long id) {
        return repository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"incorrect id")
        );
    }

    @Override
    public Barista updateBarista(long id,Barista barista) {
        barista.setId(id);
        return repository.save(barista);
    }

    @Override
    public Barista addBarista(Barista barista) {
        return repository.save(barista);
    }

    @Override
    public void deleteBarista(long id) {
        Barista barista = repository.findById(id)
                .orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"incorrect id")
                );
        repository.delete(barista);
    }
}