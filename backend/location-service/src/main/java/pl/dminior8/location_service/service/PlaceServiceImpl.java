package pl.dminior8.location_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dminior8.location_service.web.model.PlaceDTO;

import java.util.UUID;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {
    @Override
    public PlaceDTO getById(UUID placeId){
        log.debug("Getting place by id...");
        return null;
    }

    @Override
    public boolean setPlace(PlaceDTO placeDTO){
        log.debug("Setting new place...");
        return true;
    }

    @Override
    public boolean editById(UUID placeId, PlaceDTO placeDTO){
        log.debug("Editing place by id...");
        return true;
    }

    @Override
    public boolean deleteById(UUID placeId){
        log.debug("Deleting place by id...");
        return true;
    }
}
