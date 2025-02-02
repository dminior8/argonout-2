package pl.dminior8.location_service.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import pl.dminior8.location_service.service.PlaceService;
import pl.dminior8.location_service.web.model.PlaceDTO;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaceController.class)
class PlaceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private PlaceService placeService;

    @Test
    void getPlaceById() throws Exception {
        mockMvc.perform(get("/api/v1/places/" + UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void setPlace() throws Exception {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setName("Test Place"); // Ustaw prawidłową nazwę
        placeDTO.setLatitude(52.0); // Ustaw prawidłową szerokość geograficzną
        placeDTO.setLongitude(21.0); // Ustaw prawidłową długość geograficzną
        String placeDtoJson = objectMapper.writeValueAsString(placeDTO);

        mockMvc.perform(post("/api/v1/places")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(placeDtoJson))
                .andExpect(status().isOk()); // Możesz zmienić na isCreated(), jeśli to lepsze
    }

    @Test
    void editPlace() throws Exception {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setName("Test Place"); // Ustaw prawidłową nazwę
        placeDTO.setLatitude(52.0); // Ustaw prawidłową szerokość geograficzną
        placeDTO.setLongitude(21.0); // Ustaw prawidłową długość geograficzną
        String placeDtoJson = objectMapper.writeValueAsString(placeDTO);

        mockMvc.perform(put("/api/v1/places/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(placeDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void deletePlaceById() throws Exception {
        mockMvc.perform(delete("/api/v1/places/" + UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
