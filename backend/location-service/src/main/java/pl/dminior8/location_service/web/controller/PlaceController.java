package pl.dminior8.location_service.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dminior8.location_service.web.model.PlaceDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {
    @GetMapping("/{routeId}")
    public ResponseEntity<List<PlaceDTO>> getPlacesByRouteId(@PathVariable UUID routeId) {
        //return ResponseEntity.ok().body(mapService.getPlaceByRouteId(routeId));
        //TODO: Implementation
        return ResponseEntity.ok().body(new ArrayList<PlaceDTO>());
    }

    @PostMapping
    public ResponseEntity setPlace(@RequestBody PlaceDTO placeDTO) {
//        mapService.setLocation(placeWithRouteDTO);
//        return ResponseEntity.ok().body(new MessageResponse("Place edited successfully"));
        //TODO: Implementation
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/{placeId}")
    public ResponseEntity editPlace(@PathVariable UUID placeId, @RequestBody PlaceDTO placeDTO) {
//        PlaceWithRouteDTO place = mapService.editLocation(placeId, placeWithRouteDTO);
//        if(place != null){
//            return ResponseEntity.ok().body(new MessageResponse("Place edited successfully"));
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error during place editing"));
        //TODO: Implementation
        return ResponseEntity.ok().body("");
    }
}
