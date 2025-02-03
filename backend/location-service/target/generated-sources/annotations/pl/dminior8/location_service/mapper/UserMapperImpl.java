package pl.dminior8.location_service.mapper;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.dminior8.location_service.domain.ERole;
import pl.dminior8.location_service.domain.User;
import pl.dminior8.location_service.web.model.UserDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-03T00:25:19+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDTO.getId() );
        user.username( userDTO.getUsername() );
        user.email( userDTO.getEmail() );
        user.firstName( userDTO.getFirstName() );
        user.surname( userDTO.getSurname() );
        user.role( userDTO.getRole() );
        user.points( userDTO.getPoints() );
        user.createdAt( userDTO.getCreatedAt() );

        return user.build();
    }

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UUID id = null;
        String username = null;
        String email = null;
        String firstName = null;
        String surname = null;
        ERole role = null;
        Integer points = null;
        LocalDateTime createdAt = null;

        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        firstName = user.getFirstName();
        surname = user.getSurname();
        role = user.getRole();
        points = user.getPoints();
        createdAt = user.getCreatedAt();

        UserDTO userDTO = new UserDTO( id, username, email, firstName, surname, role, points, createdAt );

        return userDTO;
    }
}
