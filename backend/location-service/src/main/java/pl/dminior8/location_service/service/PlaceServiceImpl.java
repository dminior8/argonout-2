package pl.dminior8.location_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.dminior8.location_service.domain.Place;
import pl.dminior8.location_service.mapper.PlaceMapper;
import pl.dminior8.location_service.repository.PlaceRepository;
import pl.dminior8.location_service.web.exception.NotFoundException;
import pl.dminior8.location_service.web.model.PlaceDTO;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceMapper placeMapper;
    private final PlaceRepository placeRepository;

    @Override
    public PlaceDTO findById(UUID placeId){
        log.debug("Getting place by id...${placeId}");

        return placeMapper.placeToPlaceDTO(
                placeRepository.findById(placeId)
                        .orElseThrow(NotFoundException::new));

//        return new PlaceDTO(
//                placeId,
//                "Place",
//                "",
//                10.0,
//                10.0,
//                "",
//                false);
    }

    @Override
    public boolean setPlace(PlaceDTO placeDTO){
        log.debug("Setting new place...");
        placeRepository.save(placeMapper.placeDTOToPlace(placeDTO));
        return true;
    }

    @Override
    public boolean editById(UUID placeId, PlaceDTO placeDTO){
        log.debug("Editing place by id...");

        Place place = placeRepository.findById(placeId).orElseThrow(NotFoundException::new);
        place.setName(placeDTO.getName());
        place.setDescription(placeDTO.getDescription());
        place.setLatitude(placeDTO.getLatitude());
        place.setLongitude(placeDTO.getLongitude());
        place.setMoreInfoLink(placeDTO.getMoreInfoLink());
        placeRepository.save(place);


        return true;
    }

    @Override
    public boolean deleteById(UUID placeId){
        log.debug("Deleting place by id...");
        Place place = placeRepository.findById(placeId).orElseThrow(NotFoundException::new);
        place.setName("[DELETED]");
        place.setDescription(null);
        place.setLatitude(null);
        place.setLongitude(null);
        place.setMoreInfoLink(null);

        return true;
    }
}
