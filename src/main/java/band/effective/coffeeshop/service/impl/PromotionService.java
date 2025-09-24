package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Promotion;
import band.effective.coffeeshop.model.dto.PromotionRequestDTO;
import band.effective.coffeeshop.model.dto.PromotionResponseDTO;
import band.effective.coffeeshop.repository.PromotionRepository;
import band.effective.coffeeshop.service.IPromotionService;
import band.effective.coffeeshop.service.mapper.PromotionMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionService {
    private PromotionRepository repository;
    private PromotionMapper promotionMapper;
    @Override
    public Page<PromotionResponseDTO> getAllPromotions(int pageNumber,int pageSize) {
        return new PageImpl<>(repository.findAll().stream()
                .map(promotionMapper::fromEntry)
                .toList());
    }

    @Override
    public Optional<PromotionResponseDTO> getPromotionById(long id) {
        return repository.findById(id).map(promotionMapper::fromEntry);
    }

    @Override
    public PromotionResponseDTO updatePromotion(long id,PromotionRequestDTO promotion) {
        Promotion toSave = promotionMapper.toEntry(promotion);
        toSave.setId(id);
        return promotionMapper.fromEntry(repository.save(toSave));
    }

    @Override
    public PromotionResponseDTO addPromotion(PromotionRequestDTO promotion) {
        return promotionMapper.fromEntry(
                repository.save(promotionMapper.toEntry(promotion))
        );
    }

    @Override
    public void deletePromotion(long id) {
        Promotion promotion = repository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"incorrect id")
        );
        repository.delete(promotion);
    }

    @Override
    public List<Promotion> getAllPromotionsById(List<Long> promotions) {
        return repository.findAllById(promotions);
    }
}
