package pl.dminior8.game_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dminior8.game_service.domain.Game;

import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
}
