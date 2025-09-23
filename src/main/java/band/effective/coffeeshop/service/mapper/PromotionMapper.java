package band.effective.coffeeshop.service.mapper;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Promotion;
import band.effective.coffeeshop.model.dto.PromotionRequestDTO;
import band.effective.coffeeshop.model.dto.PromotionResponseDTO;
import band.effective.coffeeshop.repository.CoffeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class PromotionMapper {
    private static CoffeeMapper coffeeMapper;
    private static CoffeeRepository coffeeRepository;


    public static PromotionResponseDTO fromEntry(Promotion promotion){
        return PromotionResponseDTO.builder()
                .id(promotion.getId())
                .name(promotion.getName())
                .promotion_price(promotion.getPromotionPrice())
                .coffees(promotion.getPromotedCoffees().stream().map(CoffeeMapper::fromEntry).toList())
                .price(promotion.getPromotedCoffees().stream().map(Coffee::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add))
                .available(promotion.isAvailable())
                .build();
    }
    public static Promotion toEntry(PromotionRequestDTO requestDTO){
        return Promotion.builder()
                .name(requestDTO.getName())
                .promotionPrice(requestDTO.getPromotionPrice())
                .promotedCoffees(coffeeRepository.findAllById(requestDTO.getCoffeesId()))
                .build();
    }

}
