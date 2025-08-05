package band.effective.coffieshop.service.impl;

import band.effective.coffieshop.model.Promotion;
import band.effective.coffieshop.repository.PromotionRepository;
import band.effective.coffieshop.service.IPromotionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionService {
    private PromotionRepository repository;
    @Override
    public List<Promotion> getAllPromotions() {
        return repository.findAll();
    }

    @Override
    public Promotion getPromotionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Promotion updatePromotion(Promotion promotion) {
        return repository.save(promotion);
    }

    @Override
    public Promotion addPromotion(Promotion promotion) {
        return repository.save(promotion);
    }

    @Override
    public void deletePromotion(Promotion promotion) {
        repository.delete(promotion);
    }
}
