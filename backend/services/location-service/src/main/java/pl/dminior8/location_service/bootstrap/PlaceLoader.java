package pl.dminior8.location_service.bootstrap;

import pl.dminior8.location_service.domain.Place;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.dminior8.location_service.repository.PlaceRepository;

//@Component
public class PlaceLoader implements CommandLineRunner {
    PlaceRepository placeRepository;

    public PlaceLoader(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //loadPlaceObjects();
    }

//    private void loadPlaceObjects() {
//        if (placeRepository.count() == 0) {
//            placeRepository.save(Place.builder()
//                    .name("Central Park")
//                    .description("A large public park in New York City.")
//                    .latitude(40.785091)
//                    .longitude(-73.968285)
//                    .moreInfoLink("https://en.wikipedia.org/wiki/Central_Park")
//                    .build());
//
//            placeRepository.save(Place.builder()
//                    .name("Eiffel Tower")
//                    .description("A wrought-iron lattice tower in Paris, France.")
//                    .latitude(48.858844)
//                    .longitude(2.294350)
//                    .moreInfoLink("https://en.wikipedia.org/wiki/Eiffel_Tower")
//                    .build());
//        }
//    }
}
