package pl.dminior8.game_service.service;


import pl.dminior8.game_service.domain.GameStatus;
import pl.dminior8.game_service.web.model.GameDTO;

import java.util.UUID;

public interface GameService {

    GameDTO initGame(UUID routeId);

    GameStatus addPlaceToGame(UUID gameId, UUID placeId);

    GameStatus endGame(UUID gameId);

    GameStatus addPlaceInFreeGame(UUID placeId);
}

