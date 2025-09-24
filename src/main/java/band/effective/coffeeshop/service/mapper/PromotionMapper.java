package band.effective.coffeeshop.service.mapper;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Promotion;
import band.effective.coffeeshop.model.dto.PromotionRequestDTO;
import band.effective.coffeeshop.model.dto.PromotionResponseDTO;
import band.effective.coffeeshop.repository.CoffeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class PromotionMapper {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeMapper coffeeMapper;


    public PromotionResponseDTO fromEntry(Promotion promotion){
        return PromotionResponseDTO.builder()
                .id(promotion.getId())
                .name(promotion.getName())
                .promotion_price(promotion.getPromotionPrice())
                .coffees(promotion.getPromotedCoffees().stream().map(coffeeMapper::fromEntry).toList())
                .price(promotion.getPromotedCoffees().stream().map(Coffee::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add))
                .available(promotion.isAvailable())
                .build();
    }
    public Promotion toEntry(PromotionRequestDTO requestDTO){
        return Promotion.builder()
                .name(requestDTO.getName())
                .promotionPrice(requestDTO.getPromotionPrice())
                .promotedCoffees(coffeeRepository.findAllById(requestDTO.getCoffeesId()))
                .build();
    }

}
