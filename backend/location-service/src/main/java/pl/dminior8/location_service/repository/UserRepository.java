package pl.dminior8.location_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dminior8.location_service.domain.Place;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Place, UUID> {
}


