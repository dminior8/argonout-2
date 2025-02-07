package pl.dminior8.location_service.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.dminior8.location_service.domain.ERole;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String surname;
    private ERole role;
    private Integer points;

    @Null
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
}
