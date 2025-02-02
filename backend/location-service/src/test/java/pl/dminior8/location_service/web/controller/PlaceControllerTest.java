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
        String placeDtoJson = objectMapper.writeValueAsString(getValidDTO());

        mockMvc.perform(post("/api/v1/places")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(placeDtoJson))
                .andExpect(status().isOk()); // Możesz zmienić na isCreated(), jeśli to lepsze
    }

    @Test
    void editPlace() throws Exception {
        String placeDtoJson = objectMapper.writeValueAsString(getValidDTO());

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

    private PlaceDTO getValidDTO(){
        return PlaceDTO.builder()
                .name("Test Place")
                .latitude(52.0)
                .longitude(21.0)
                .build();
    }
}
