package pl.dminior8.location_service.mapper;

import org.mapstruct.Mapper;
import pl.dminior8.location_service.domain.User;
import pl.dminior8.location_service.web.model.UserDTO;

@Mapper
public interface UserMapper {
    User userDTOToUser(UserDTO userDTO);
    UserDTO userToUserDTO(User user);
}
