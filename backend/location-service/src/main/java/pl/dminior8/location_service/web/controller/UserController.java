package pl.dminior8.location_service.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dminior8.location_service.web.model.PlaceDTO;
import pl.dminior8.location_service.web.model.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/{userId}")
    public ResponseEntity<List<PlaceDTO>> getUserById(@PathVariable UUID userId) {
        //TODO: implementation
        return ResponseEntity.ok().body(new ArrayList<PlaceDTO>());
    }

    @PostMapping("/{userId}")
    public ResponseEntity setUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok().body("");
    }

    @PatchMapping("/{userId}")
    public ResponseEntity editUserById(@PathVariable UUID userId, @RequestBody UserDTO userDTO) {

        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUserById(@PathVariable UUID userId){
        return ResponseEntity.ok().body("");
    }
}

