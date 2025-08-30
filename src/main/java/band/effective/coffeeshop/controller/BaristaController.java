package band.effective.coffeeshop.controller;

import band.effective.coffeeshop.model.Barista;
import band.effective.coffeeshop.service.IBaristaService;
import band.effective.coffeeshop.service.impl.BaristaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baristas")
@AllArgsConstructor
public class BaristaController {
    private final IBaristaService service;
    @GetMapping
    public List<Barista> getAllBaristas(){
        return service.getAllBaristas();
    }
    @GetMapping("/{id}")
    public Barista getBaristaById(@PathVariable Long id){
        return service.getBaristaById(id);
    }
    @PostMapping
    public Barista addBarista(@RequestBody Barista barista){
        return service.addBarista(barista);
    }
    @PutMapping("/{id}")
    public  Barista updateBarista(@PathVariable Long id, @RequestBody Barista barista){
        barista.setId(id);
        return service.updateBarista(barista);
    }
    @DeleteMapping("/{id}")
    public void deleteBarista(@PathVariable Long id){
        service.deleteBarista(service.getBaristaById(id));
    }
}