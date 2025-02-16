package pl.dminior8.game_service.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.dminior8.game_service.domain.GameStatus;
import pl.dminior8.game_service.service.GameService;
import pl.dminior8.game_service.web.model.GameDTO;
import pl.dminior8.game_service.web.model.MessageResponse;

import java.sql.Timestamp;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    GameService gameService;

    private final UUID SAMPLE_GAME_ID = UUID.randomUUID();
    private final UUID SAMPLE_PLACE_ID = UUID.randomUUID();
    private final UUID SAMPLE_ROUTE_ID = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        given(gameService.initGame(any())).willReturn(GameDTO.builder()
                .id(SAMPLE_GAME_ID)
                .routeId(SAMPLE_ROUTE_ID)
                .startTime(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    @Test
    void initGame() throws Exception {
        ConstrainedFields fields = new ConstrainedFields(GameDTO.class);

        mockMvc.perform(post("/api/v1/game/adventure/init/{routeId}", SAMPLE_ROUTE_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("adventure_mode-init",
                        pathParameters(
                                parameterWithName("routeId").description("ID of the route to initialize game for")
                        ),
                        responseFields(
                                fields.withPath("id").description("UUID of the game").type(UUID.class),
                                fields.withPath("routeId").description("UUID of the route").type(UUID.class),
                                fields.withPath("startTime").description("Timestamp when the game started").type(Timestamp.class)
                        )));
    }

    @Test
    void addPlaceToGame_Success() throws Exception {
        testAddPlaceScenario(GameStatus.SUCCESS, HttpStatus.OK, "Place added successfully");
    }

    @Test
    void addPlaceToGame_TimeUp() throws Exception {
        testAddPlaceScenario(GameStatus.TIME_UP, HttpStatus.REQUEST_TIMEOUT, "Time is up");
    }

    @Test
    void addPlaceToGame_NotFound() throws Exception {
        testAddPlaceScenario(GameStatus.GAME_NOT_FOUND, HttpStatus.NOT_FOUND, "Game or place not found");
    }

    @Test
    void addPlaceToGame_PlaceNotInGame() throws Exception {
        testAddPlaceScenario(GameStatus.PLACE_NOT_IN_GAME, HttpStatus.FORBIDDEN, "Place not part of this game");
    }

    @Test
    void addPlaceToGame_Duplicate() throws Exception {
        testAddPlaceScenario(GameStatus.DUPLICATE_PLACE, HttpStatus.CONFLICT, "Place already added");
    }

    private void testAddPlaceScenario(GameStatus status, HttpStatus httpStatus, String message) throws Exception {
        ConstrainedFields fields = new ConstrainedFields(MessageResponse.class);

        given(gameService.addPlaceToGame(any(), any())).willReturn(status);

        mockMvc.perform(post("/api/v1/game/adventure/{gameId}/add-place/{placeId}", SAMPLE_GAME_ID, SAMPLE_PLACE_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(httpStatus.value()))
                .andDo(document("adventure_mode-add-place-" + status.name().toLowerCase(),
                        pathParameters(
                                parameterWithName("gameId").description("ID of the game"),
                                parameterWithName("placeId").description("ID of the place to add")
                        ),
                        responseFields(
                                fields.withPath("message").description(message)
                        )));
    }

    @Test
    void endGame_Success() throws Exception {
        testEndGameScenario(GameStatus.SUCCESS, "Game ended - all places collected", HttpStatus.OK);
    }

    @Test
    void endGame_Incomplete() throws Exception {
        testEndGameScenario(GameStatus.INCOMPLETE, "Game ended - failed to visit all places", HttpStatus.OK);
    }

    @Test
    void endGame_NotFound() throws Exception {
        testEndGameScenario(GameStatus.GAME_NOT_FOUND, "Game not found", HttpStatus.NOT_FOUND);
    }

    private void testEndGameScenario(GameStatus status, String message, HttpStatus expectedStatus) throws Exception {
        ConstrainedFields fields = new ConstrainedFields(MessageResponse.class);

        given(gameService.endGame(any())).willReturn(status);

        mockMvc.perform(post("/api/v1/game/adventure/{gameId}/end", SAMPLE_GAME_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(expectedStatus.value())) // Dostosuj oczekiwany status
                .andDo(document("adventure_mode-end-" + status.name().toLowerCase(),
                        pathParameters(
                                parameterWithName("gameId").description("ID of the game to end")
                        ),
                        responseFields(
                                fields.withPath("message").description(message)
                        )));
    }

    @Test
    void addPlaceInFreeGame_Success() throws Exception {
        testFreeGameScenario(GameStatus.SUCCESS, HttpStatus.OK, "Place visited successfully");
    }

    @Test
    void addPlaceInFreeGame_NotFound() throws Exception {
        testFreeGameScenario(GameStatus.PLACE_NOT_FOUND, HttpStatus.NOT_FOUND, "Place not found");
    }

    @Test
    void addPlaceInFreeGame_Duplicate() throws Exception {
        testFreeGameScenario(GameStatus.DUPLICATE_PLACE, HttpStatus.TOO_MANY_REQUESTS, "Place already visited today");
    }

    private void testFreeGameScenario(GameStatus status, HttpStatus httpStatus, String message) throws Exception {
        ConstrainedFields fields = new ConstrainedFields(MessageResponse.class);

        given(gameService.addPlaceInFreeGame(any())).willReturn(status);

        mockMvc.perform(post("/api/v1/game/free/add-place/{placeId}", SAMPLE_PLACE_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(httpStatus.value()))
                .andDo(document("free_mode-add-" + status.name().toLowerCase(),
                        pathParameters(
                                parameterWithName("placeId").description("ID of the place to add")
                        ),
                        responseFields(
                                fields.withPath("message").description(message)
                        )));
    }

    private static final class ConstrainedFields {
        private final ConstraintDescriptions constraintDescriptions;

        public ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        public FieldDescriptor withPath(String path) {
            return fieldWithPath(path)
                    .attributes(
                            key("constraints")
                                    .value(
                                            collectionToDelimitedString(
                                                    this.constraintDescriptions.descriptionsForProperty(path), "- ")));
        }
    }
}
