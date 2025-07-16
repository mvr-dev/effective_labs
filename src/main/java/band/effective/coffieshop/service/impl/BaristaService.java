package band.effective.coffieshop.service.impl;

import band.effective.coffieshop.model.Barista;
import band.effective.coffieshop.repository.BaristaRepository;
import band.effective.coffieshop.service.IBaristaService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
    public Barista getBaristaById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Barista updateBarista(Barista barista) {
        return repository.save(barista);
    }

    @Override
    public Barista addBarista(Barista barista) {
        return repository.save(barista);
    }

    @Override
    public void deleteBarista(Barista barista) {
        repository.delete(barista);
    }
}