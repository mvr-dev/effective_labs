package band.effective.coffieshop.model.dto;

import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.model.Promotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionResponseDTO {
    private Long id;
    private List<CoffeeResponseDTO> coffees;
    private Double price;
    private Double promotion_price;
    private String name;
    private boolean available;

    public static PromotionResponseDTO fromEntry(Promotion promotion){
        return PromotionResponseDTO.builder()
                .id(promotion.getId())
                .name(promotion.getName())
                .promotion_price(promotion.getPromotionPrice())
                .coffees(promotion.getPromotedCoffees().stream().map(CoffeeResponseDTO::fromEntry).toList())
                .price(promotion.getPromotedCoffees().stream().mapToDouble(Coffee::getPrice).sum())
                .available(promotion.isAvailable())
                .build();
    }
}
