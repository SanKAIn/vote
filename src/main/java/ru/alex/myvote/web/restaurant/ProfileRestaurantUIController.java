package ru.alex.myvote.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.myvote.to.RestaurantTo;

import java.util.List;

@RestController
@RequestMapping("/ajax/profile/restaurants")
public class ProfileRestaurantUIController extends AbstractRestaurantController {

    @Override
    @GetMapping
    public List<RestaurantTo> getAll(){
        List<RestaurantTo> restaurant = super.getAll();
        //return super.getAll();
        return restaurant;
    }

    @Override
    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> vote(@PathVariable int id) {
        return super.vote(id);
    }
}
