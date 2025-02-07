package pl.dminior8.location_service.service;

import pl.dminior8.location_service.web.model.PlaceDTO;

import java.util.UUID;

public interface PlaceService {
    PlaceDTO findById(UUID placeId);

    boolean setPlace(PlaceDTO placeDTO);

    boolean editById(UUID placeId, PlaceDTO placeDTO);

    boolean deleteById(UUID placeId);
}
