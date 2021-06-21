package pl.bm.bmyszkiewiczprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bm.bmyszkiewiczprojekt.entity.Deliverer;

@Repository
public interface DelivererRepository extends JpaRepository<Deliverer, Long> {
}
