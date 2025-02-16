package pl.dminior8.game_service.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dminior8.game_service.domain.*;
import pl.dminior8.game_service.mapper.GameMapper;
import pl.dminior8.game_service.repository.GameRepository;
import pl.dminior8.game_service.repository.VisitedPlaceRepository;
import pl.dminior8.game_service.web.model.GameDTO;

import java.util.UUID;

//TODO: Fix when connection with Location service
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final VisitedPlaceRepository visitedPlaceRepository;
    private final GameMapper gameMapper;
//    private final RestTemplate restTemplate; // Klient HTTP do komunikacji z mikroserwisem Route i Place
//    private final String ROUTE_SERVICE_URL = "http://route-service/api/routes";
//    private final String PLACE_SERVICE_URL = "http://place-service/api/places";

    @Override
    @Transactional
    public GameDTO initGame(UUID routeId) {
        // Sprawdź, czy trasa istnieje w zewnętrznym mikroserwisie
//        boolean routeExists = Boolean.TRUE.equals(restTemplate.getForObject(
//                ROUTE_SERVICE_URL + "/exists/{routeId}",
//                Boolean.class,
//                routeId
//        ));
//
//        if (!routeExists) {
//            throw new IllegalArgumentException("Route not found");
//        }

        Game game = Game.builder()
                .routeId(routeId)
                .isCompleted(false)
                .build();
        return gameMapper.gameToGameDTO(gameRepository.save(game));
    }

    @Override
    @Transactional
    public GameStatus addPlaceToGame(UUID gameId, UUID placeId) {
        Game game = gameRepository.findById(gameId)
                .orElse(null);
        if (game == null) return GameStatus.GAME_NOT_FOUND;

        // Sprawdź, czy miejsce istnieje w zewnętrznym mikroserwisie
//        boolean placeExists = Boolean.TRUE.equals(restTemplate.getForObject(
//                PLACE_SERVICE_URL + "/exists/{placeId}",
//                Boolean.class,
//                placeId
//        ));
//
//        if (!placeExists) {
//            return AddPlaceStatus.PLACE_NOT_FOUND.ordinal();
//        }

        // Sprawdź, czy miejsce należy do trasy w zewnętrznym mikroserwisie
//        boolean placeBelongsToRoute = Boolean.TRUE.equals(restTemplate.getForObject(
//                ROUTE_SERVICE_URL + "/{routeId}/contains-place/{placeId}",
//                Boolean.class,
//                game.getRouteId(),
//                placeId
//        ));

//        if (!placeBelongsToRoute) {
//            return AddPlaceStatus.PLACE_NOT_IN_GAME.ordinal();
//        }

        if (visitedPlaceRepository.existsByGameIdAndPlaceId(gameId, placeId))
            return GameStatus.DUPLICATE_PLACE;

        if (isGameTimeExpired(game))
            return GameStatus.TIME_UP;

        saveVisitedPlace(gameId, placeId);
        return GameStatus.SUCCESS;
    }

    @Override
    @Transactional
    public GameStatus endGame(UUID gameId) {
        Game game = gameRepository.findById(gameId)
                .orElse(null);
        if (game == null) return GameStatus.GAME_NOT_FOUND;

        // Pobierz listę miejsc dla trasy z zewnętrznego mikroserwisu
//        List<UUID> routePlaces = restTemplate.getForObject(
//                ROUTE_SERVICE_URL + "/{routeId}/places",
//                List.class,
//                game.getRouteId()
//        );
//
//        if (routePlaces == null || routePlaces.isEmpty()) {
//            return false;
//        }
//
//        List<UUID> visitedPlaces = visitedPlaceRepository.findPlaceIdsByGameId(gameId);
//
//        boolean allPlacesVisited = routePlaces.stream()
//                .allMatch(visitedPlaces::contains);

//        if (allPlacesVisited) {
            game.setCompleted(true);
            gameRepository.save(game);
            return GameStatus.SUCCESS;
       // }

//        return GameStatus.INCOMPLETE;
    }

    @Override
    @Transactional
    public GameStatus addPlaceInFreeGame(UUID placeId) {
        // Sprawdź, czy miejsce istnieje w zewnętrznym mikroserwisie
//        boolean placeExists = Boolean.TRUE.equals(restTemplate.getForObject(
//                PLACE_SERVICE_URL + "/exists/{placeId}",
//                Boolean.class,
//                placeId
//        ));

//        if (!placeExists) {
//            return GameStatus.PLACE_NOT_FOUND;
//        }

        saveVisitedPlace(null, placeId);
        return GameStatus.SUCCESS;
    }

    private boolean isGameTimeExpired(Game game) {
        // Pobierz maksymalny czas trwania gry z zewnętrznego mikroserwisu
//        Integer maxTime = restTemplate.getForObject(
//                ROUTE_SERVICE_URL + "/{routeId}/max-time",
//                Integer.class,
//                game.getRouteId()
//        );

//        if (maxTime == null) {
//            return GameStatus.TIME_UP; // Jeśli nie można pobrać czasu, uznajemy, że gra wygasła
//        }

//        return LocalDateTime.now().isAfter(game.getStartTime().toLocalDateTime().plusMinutes(maxTime));
        return true;
    }

    private void saveVisitedPlace(UUID gameId, UUID placeId) {
        VisitedPlace visitedPlace = VisitedPlace.builder()
                .gameId(gameId)
                .placeId(placeId)
                .build();
        visitedPlaceRepository.save(visitedPlace);
    }
}