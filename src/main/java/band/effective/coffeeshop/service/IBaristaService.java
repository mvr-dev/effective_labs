package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Barista;

import java.util.List;

public interface IBaristaService {
    List<Barista> getAllBaristas();
    Barista getBaristaById(Long id);
    Barista updateBarista(Barista barista);
    Barista addBarista(Barista barista);
    void deleteBarista(Barista barista);
}