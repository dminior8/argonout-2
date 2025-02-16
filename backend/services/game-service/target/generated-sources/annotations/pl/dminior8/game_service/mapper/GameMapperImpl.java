package pl.dminior8.game_service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.dminior8.game_service.domain.Game;
import pl.dminior8.game_service.web.model.GameDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-09T23:56:59+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Override
    public GameDTO gameToGameDTO(Game place) {
        if ( place == null ) {
            return null;
        }

        GameDTO.GameDTOBuilder gameDTO = GameDTO.builder();

        gameDTO.id( place.getId() );
        gameDTO.routeId( place.getRouteId() );
        gameDTO.startTime( place.getStartTime() );

        return gameDTO.build();
    }
}
