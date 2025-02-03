package pl.dminior8.location_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dminior8.location_service.web.model.UserDTO;
import pl.dminior8.location_service.web.model.PlaceDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public List<PlaceDTO> getById(UUID userId) {
        log.debug("Fetching places for user with ID: {}", userId);
        return new ArrayList<>();
    }

    @Override
    public boolean setUser(UUID userId) {
        log.debug("Setting user with ID: {}", userId);
        return true;
    }

    @Override
    public boolean editById(UUID userId, UserDTO userDTO) {
        log.debug("Editing user with ID: {}", userId);
        return true;
    }

    @Override
    public boolean deleteById(UUID userId) {
        log.debug("Deleting user with ID: {}", userId);
        return true;
    }
}