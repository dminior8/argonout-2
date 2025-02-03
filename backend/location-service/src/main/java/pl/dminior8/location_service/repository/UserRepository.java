package pl.dminior8.location_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dminior8.location_service.domain.Place;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Place, UUID> {
}


