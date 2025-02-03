package pl.dminior8.location_service.web.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dminior8.location_service.service.PlaceService;
import pl.dminior8.location_service.web.model.PlaceDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<List<PlaceDTO>> getPlacesByRouteId(@NotNull @PathVariable UUID placeId) {
        PlaceDTO place = placeService.getById(placeId);
        //return ResponseEntity.ok().body(mapService.getPlaceByRouteId(routeId));
        //TODO: Implementation
        return ResponseEntity.ok().body(new ArrayList<PlaceDTO>());
    }

    @PostMapping
    public ResponseEntity setPlace(@Valid @NonNull @RequestBody PlaceDTO placeDTO) {
        placeService.setPlace(placeDTO);
//        mapService.setLocation(placeWithRouteDTO);
//        return ResponseEntity.ok().body(new MessageResponse("Place edited successfully"));
        //TODO: Implementation
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/{placeId}")
    public ResponseEntity editPlace(@NotNull @PathVariable UUID placeId, @Valid @RequestBody PlaceDTO placeDTO) {
        placeService.editById(placeId, placeDTO);
//        if(place != null){
//            return ResponseEntity.ok().body(new MessageResponse("Place edited successfully"));
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error during place editing"));
        //TODO: Implementation
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity deletePlace(@NotNull @PathVariable UUID placeId) {
        placeService.deleteById(placeId);
//        if(mapService.deletePlace(placeId)){
//            return ResponseEntity.ok().body(new MessageResponse("Place deleted successfully"));
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Error during place deleting"));
        //TODO: Implementation
        return ResponseEntity.ok().body("");
    }

}
