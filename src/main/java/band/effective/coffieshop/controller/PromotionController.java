package band.effective.coffieshop.controller;

import band.effective.coffieshop.model.CustomerOrder;
import band.effective.coffieshop.model.Promotion;
import band.effective.coffieshop.model.dto.PromotionRequestDTO;
import band.effective.coffieshop.model.dto.PromotionResponseDTO;
import band.effective.coffieshop.service.IPromotionService;
import band.effective.coffieshop.service.impl.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/promo")
@AllArgsConstructor
public class PromotionController {
    private IPromotionService service;

    private final CoffeeService coffeeService;

    @GetMapping
    public List<PromotionResponseDTO> getAllPromotions(){
        return service.getAllPromotions().stream()
                .map(PromotionResponseDTO::fromEntry)
                .toList();
    }

    @GetMapping("/{id}")
    public PromotionResponseDTO getPromotionById(@PathVariable Long id){
        return PromotionResponseDTO.fromEntry(service.getPromotionById(id));
    }

    @PostMapping
    public PromotionResponseDTO postPromotion(@RequestBody PromotionRequestDTO promotionRequestDTO){
        Promotion promotion = Promotion.builder()
                .name(promotionRequestDTO.getName())
                .promotionPrice(promotionRequestDTO.getPromotionPrice())
                .promotedCoffees(coffeeService.getAllCoffeesById(promotionRequestDTO.getCoffeesId()))
                .available(true)
                .build();
        service.addPromotion(promotion);
        return PromotionResponseDTO.fromEntry(promotion);
    }

    @PutMapping("/{id}")
    public PromotionResponseDTO updatePromotion(@PathVariable Long id,@RequestBody PromotionRequestDTO requestDTO){
        var promotion = service.getPromotionById(id);
        if(requestDTO.getPromotionPrice()!=null){
            promotion.setPromotionPrice(requestDTO.getPromotionPrice());
        }
        if (requestDTO.getName()!=null){
            promotion.setName(requestDTO.getName());
        }
        if(requestDTO.getCoffeesId()!=null){
            promotion.setPromotedCoffees(coffeeService.getAllCoffeesById(requestDTO.getCoffeesId()));
        }
        service.updatePromotion(promotion);
        return PromotionResponseDTO.fromEntry(promotion);
    }

    @DeleteMapping("/{id}")
    public void deletePromotion(@PathVariable Long id) {
        var promotion = service.getPromotionById(id);
        if (promotion!=null) {
            promotion.setAvailable(false);
        }
    }

}
