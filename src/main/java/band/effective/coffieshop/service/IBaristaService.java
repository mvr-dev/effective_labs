package band.effective.coffieshop.service;

import band.effective.coffieshop.model.Barista;

import java.util.List;

public interface IBaristaService {
    List<Barista> getAllBaristas();
    Barista getBaristaById(Long id);
    Barista updateBarista(Barista barista);
    Barista addBarista(Barista barista);
    void deleteBarista(Barista barista);
}