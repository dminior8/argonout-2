package pl.dminior8.location_service.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceDTO {

    @Null //only program can generate id
    private UUID id;

    @NotBlank //not null, not "" and not white space
    @Size(min = 2, max = 100)
    private String name;

    @Size(min = 2, max = 1000)
    private String description;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private String moreInfoLink;

    @NotNull
    private boolean isVisited;
}