package band.effective.coffeeshop.repository;

import band.effective.coffeeshop.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion,Long> {
}
