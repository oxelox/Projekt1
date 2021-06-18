package pl.bm.bmyszkiewiczprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bm.bmyszkiewiczprojekt.entity.AppUserDetails;

@Repository
public interface AppUserDetailsRepository extends JpaRepository<AppUserDetails, Long> {
}
