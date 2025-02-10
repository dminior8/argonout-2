package pl.dminior8.game_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dminior8.game_service.domain.VisitedPlace;

import java.util.List;
import java.util.UUID;

@Repository
public interface VisitedPlaceRepository extends JpaRepository<VisitedPlace, UUID> {
    boolean existsByGameIdAndPlaceId(UUID gameId, UUID placeId);

    List<UUID> findPlaceIdsByGameId(UUID gameId);
}
