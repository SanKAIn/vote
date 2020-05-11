package ru.alex.myvote.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.myvote.to.RestaurantTo;

import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/profile/restaurants";

    @Override
    @GetMapping
    public List<RestaurantTo> getAll(){
        return super.getAll();
    }

    @Override
    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> vote(@PathVariable int id) {
        return super.vote(id);
    }
}
