package pl.dminior8.game_service.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dminior8.game_service.service.GameService;
import pl.dminior8.game_service.domain.GameStatus;
import pl.dminior8.game_service.web.model.GameDTO;
import pl.dminior8.game_service.web.model.MessageResponse;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game")
public class GameController {
    private final GameService gameService;

    @PostMapping("/adventure/init/{routeId}")
    public ResponseEntity<GameDTO> initGame(@PathVariable UUID routeId) {
        return ResponseEntity.ok(gameService.initGame(routeId));
    }

    @PostMapping("/adventure/{gameId}/add-place/{placeId}")
    public ResponseEntity<MessageResponse> addPlaceToGame(
            @PathVariable UUID gameId,
            @PathVariable UUID placeId) {

        GameStatus status = gameService.addPlaceToGame(gameId, placeId);
        return switch (status) {
            case SUCCESS -> ResponseEntity.ok(new MessageResponse("Place added successfully"));
            case TIME_UP -> ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body(new MessageResponse("Time is up"));
            case GAME_NOT_FOUND, PLACE_NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Game or place not found"));
            case PLACE_NOT_IN_GAME -> ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("Place not part of this game"));
            case DUPLICATE_PLACE -> ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Place already added"));
            default -> ResponseEntity.badRequest()
                    .body(new MessageResponse("Unknown error"));
        };
    }

    @PostMapping("/adventure/{gameId}/end")
    public ResponseEntity<MessageResponse> endGame(@PathVariable UUID gameId) {
        GameStatus status = gameService.endGame(gameId);
        return switch (status) {
            case SUCCESS -> ResponseEntity.ok(new MessageResponse("Game ended - all places collected"));
            case INCOMPLETE -> ResponseEntity.ok(new MessageResponse("Game ended - failed to visit all places"));
            case GAME_NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Game not found"));
            default -> ResponseEntity.badRequest()
                    .body(new MessageResponse("Unknown error"));
        };
    }

    @PostMapping("/free/add-place/{placeId}")
    public ResponseEntity<MessageResponse> addPlaceInFreeGame(@PathVariable UUID placeId) {
        GameStatus status = gameService.addPlaceInFreeGame(placeId);
        return switch (status) {
            case SUCCESS -> ResponseEntity.ok(new MessageResponse("Place visited successfully"));
            case PLACE_NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Place not found"));
            case DUPLICATE_PLACE -> ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(new MessageResponse("Place already visited today"));
            default -> ResponseEntity.badRequest()
                    .body(new MessageResponse("Unknown error"));
        };
    }
}