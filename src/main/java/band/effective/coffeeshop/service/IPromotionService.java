package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Promotion;
import band.effective.coffeeshop.model.dto.PromotionRequestDTO;
import band.effective.coffeeshop.model.dto.PromotionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public interface IPromotionService {
    Page<PromotionResponseDTO> getAllPromotions(int pageNumber, int pageSize);
    Optional<PromotionResponseDTO> getPromotionById(long id);
    PromotionResponseDTO updatePromotion(long id,PromotionRequestDTO promotion);
    PromotionResponseDTO addPromotion(PromotionRequestDTO promotion);
    void deletePromotion(long id);

    List<Promotion> getAllPromotionsById(List<Long> promotions);
}
