package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Barista;

import java.util.List;

public interface IBaristaService {
    List<Barista> getAllBaristas();
    Barista getBaristaById(long id);
    Barista updateBarista(long id,Barista barista);
    Barista addBarista(Barista barista);
    void deleteBarista(long id);
}