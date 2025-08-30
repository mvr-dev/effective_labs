package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Promotion;
import band.effective.coffeeshop.repository.PromotionRepository;
import band.effective.coffeeshop.service.IPromotionService;
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
    public List<Promotion> getAllPromotionsById(Iterable<Long> id){
        return repository.findAllById(id);
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
