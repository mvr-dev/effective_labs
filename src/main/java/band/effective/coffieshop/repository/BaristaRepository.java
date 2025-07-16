package band.effective.coffieshop.repository;

import band.effective.coffieshop.model.Barista;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaristaRepository extends JpaRepository<Barista,Long> {


}
