package pl.bm.bmyszkiewiczprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bm.bmyszkiewiczprojekt.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    void deleteByToken(String token);
}
