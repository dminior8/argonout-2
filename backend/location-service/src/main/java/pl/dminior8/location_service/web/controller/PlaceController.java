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
    public ResponseEntity<PlaceDTO> getPlaceById(@NotNull @PathVariable UUID placeId) {
        PlaceDTO placeDTO = placeService.findById(placeId);
        return ResponseEntity.ok().body(placeDTO);
    }

    @PostMapping
    public ResponseEntity setPlace(@Valid @NonNull @RequestBody PlaceDTO placeDTO) {
        placeService.setPlace(placeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(placeDTO);
//        }else{
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(placeDTO);
//        }
    }

    @PutMapping("/{placeId}")
    public ResponseEntity editPlace(@NotNull @PathVariable UUID placeId, @Valid @RequestBody PlaceDTO placeDTO) {
        placeService.editById(placeId, placeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(placeDTO);
//        }else{
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(placeDTO);
//        }
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
