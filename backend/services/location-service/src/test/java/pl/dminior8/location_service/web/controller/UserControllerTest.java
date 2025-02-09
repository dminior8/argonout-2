package pl.dminior8.location_service.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.dminior8.location_service.service.UserService;
import pl.dminior8.location_service.web.model.UserDTO;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/api/v1/user/" + UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void setUser() throws Exception {
        UserDTO userDTO = new UserDTO(UUID.randomUUID(), "TestUser", "test@example.com", "John", "Doe", null, 100, null);
        String userDtoJson = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/api/v1/user/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void editUserById() throws Exception {
        UserDTO userDTO = new UserDTO(UUID.randomUUID(), "TestUser", "test@example.com", "John", "Doe", null, 100, null);
        String userDtoJson = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(patch("/api/v1/user/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserById() throws Exception {
        mockMvc.perform(delete("/api/v1/user/" + UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}