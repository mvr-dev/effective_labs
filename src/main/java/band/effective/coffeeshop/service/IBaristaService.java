package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Barista;
import org.springframework.data.domain.Page;

public interface IBaristaService {
    Page<Barista> getAllBaristas(int pageNumber,int PageSize);
    Barista getBaristaById(long id);
    Barista updateBarista(long id,Barista barista);
    Barista addBarista(Barista barista);
    void deleteBarista(long id);
}