package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IPromotionService {
    List<Promotion> getAllPromotions();
    Promotion getPromotionById(Long id);
    Promotion updatePromotion(Promotion promotion);
    Promotion addPromotion(Promotion promotion);
    void deletePromotion(Promotion promotion);
}
