package band.effective.coffeeshop.controller;

import band.effective.coffeeshop.model.Promotion;
import band.effective.coffeeshop.model.dto.PromotionRequestDTO;
import band.effective.coffeeshop.model.dto.PromotionResponseDTO;
import band.effective.coffeeshop.service.IPromotionService;
import band.effective.coffeeshop.service.impl.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/promo")
@AllArgsConstructor
public class PromotionController {
    private IPromotionService service;


    @GetMapping
    public Page<PromotionResponseDTO> getAllPromotions(@RequestParam(value = "offset",defaultValue = "0") int pageNumber,
                                                       @RequestParam(value = "limit", defaultValue = "20") int pageSize){
        return service.getAllPromotions(pageNumber,pageSize);
    }

    @GetMapping("/{id}")
    public PromotionResponseDTO getPromotionById(@PathVariable long id){
        return service.getPromotionById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"incorrect id")
        );
    }

    @PostMapping
    public PromotionResponseDTO postPromotion(@RequestBody PromotionRequestDTO promotionRequestDTO){
        return service.addPromotion(promotionRequestDTO);
    }

    @PutMapping("/{id}")
    public PromotionResponseDTO updatePromotion(@PathVariable long id,@RequestBody PromotionRequestDTO requestDTO){
        return service.updatePromotion(id,requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePromotion(@PathVariable long id) {
        service.deletePromotion(id);
    }

}
