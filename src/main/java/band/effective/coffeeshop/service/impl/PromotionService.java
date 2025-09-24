package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Promotion;
import band.effective.coffeeshop.model.dto.PromotionRequestDTO;
import band.effective.coffeeshop.model.dto.PromotionResponseDTO;
import band.effective.coffeeshop.repository.PromotionRepository;
import band.effective.coffeeshop.service.IPromotionService;
import band.effective.coffeeshop.service.mapper.PromotionMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionService {
    private PromotionRepository repository;
    //private PromotionMapper mapper;
    @Override
    public List<PromotionResponseDTO> getAllPromotions() {
        return repository.findAll().stream()
                .map(PromotionMapper::fromEntry)
                .toList();
    }

    @Override
    public Optional<PromotionResponseDTO> getPromotionById(long id) {
        return repository.findById(id).map(PromotionMapper::fromEntry);
    }

    @Override
    public PromotionResponseDTO updatePromotion(long id,PromotionRequestDTO promotion) {
        Promotion toSave = PromotionMapper.toEntry(promotion);
        toSave.setId(id);
        return PromotionMapper.fromEntry(repository.save(toSave));
    }

    @Override
    public PromotionResponseDTO addPromotion(PromotionRequestDTO promotion) {
        return PromotionMapper.fromEntry(
                repository.save(PromotionMapper.toEntry(promotion))
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
