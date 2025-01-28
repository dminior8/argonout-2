package pl.dminior8.location_service.web.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceDTO {
    private UUID id;

    private String name;

    private String description;

    private Double latitude;

    private Double longitude;

    private String moreInfoLink;

    private boolean isVisited;
}