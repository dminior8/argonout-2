package pl.dminior8.location_service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.dminior8.location_service.domain.Place;
import pl.dminior8.location_service.web.model.PlaceDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T00:51:11+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class PlaceMapperImpl implements PlaceMapper {

    @Override
    public PlaceDTO placeToPlaceDTO(Place place) {
        if ( place == null ) {
            return null;
        }

        PlaceDTO.PlaceDTOBuilder placeDTO = PlaceDTO.builder();

        placeDTO.id( place.getId() );
        placeDTO.name( place.getName() );
        placeDTO.description( place.getDescription() );
        placeDTO.latitude( place.getLatitude() );
        placeDTO.longitude( place.getLongitude() );
        placeDTO.moreInfoLink( place.getMoreInfoLink() );

        return placeDTO.build();
    }

    @Override
    public Place placeDTOToPlace(PlaceDTO placeDTO) {
        if ( placeDTO == null ) {
            return null;
        }

        Place.PlaceBuilder place = Place.builder();

        place.id( placeDTO.getId() );
        place.name( placeDTO.getName() );
        place.description( placeDTO.getDescription() );
        place.latitude( placeDTO.getLatitude() );
        place.longitude( placeDTO.getLongitude() );
        place.moreInfoLink( placeDTO.getMoreInfoLink() );

        return place.build();
    }
}
