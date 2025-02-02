package pl.dminior8.location_service.service;

import pl.dminior8.location_service.web.model.PlaceDTO;
import pl.dminior8.location_service.web.model.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface UserService {

    List<PlaceDTO> getById(UUID userId);

    boolean setUser(UUID userId);

    boolean editById(UUID userId, UserDTO userDTO);

    boolean deleteById(UUID userId);
}
