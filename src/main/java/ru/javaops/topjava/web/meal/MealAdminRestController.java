package ru.javaops.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.service.MealService;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static ru.javaops.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javaops.topjava.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MealAdminRestController.REST_URL)
public class MealAdminRestController {
    static final String REST_URL = "/rest/admin/meals";

    private final MealService mealService;

    public MealAdminRestController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/{id}")
    public Meal get(@NotNull @PathVariable Integer id) {
        return mealService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        mealService.delete(id);
    }

    @GetMapping
    public List<Meal> getAll() {
        return mealService.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        assureIdConsistent(meal, id);
        mealService.update(meal, meal.getRestaurant().getId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        checkNew(meal);
        Meal created = mealService.create(meal, meal.getRestaurant().getId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
