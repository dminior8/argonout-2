package pl.dminior8.game_service.mapper;

import org.mapstruct.Mapper;
import pl.dminior8.game_service.domain.Game;
import pl.dminior8.game_service.web.model.GameDTO;

@Mapper
public interface GameMapper {
    GameDTO gameToGameDTO(Game place);
}
