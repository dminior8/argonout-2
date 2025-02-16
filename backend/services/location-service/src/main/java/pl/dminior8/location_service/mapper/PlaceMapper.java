package pl.dminior8.location_service.mapper;

import org.mapstruct.Mapper;
import pl.dminior8.location_service.domain.Place;
import pl.dminior8.location_service.web.model.PlaceDTO;

@Mapper//(uses =  {DateMapper.class}) - when date conversion, need to create class and add here
public interface PlaceMapper {
    PlaceDTO placeToPlaceDTO(Place place);
    Place placeDTOToPlace(PlaceDTO placeDTO);
}
