package ru.javaops.topjava.web.menu;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava.model.Menu;
import ru.javaops.topjava.to.MenuTo;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javaops.topjava.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = MenuAdminRestController.REST_URL)
public class MenuAdminRestController extends AbstractMenuRestController {
    static final String REST_URL = "/rest/admin/menu";

    @GetMapping(value = "/now", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getToday() {
        return super.getToday();
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getByDate(@NotNull @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return menuService.getByDate(date);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAll() {
        return menuService.getAll();
    }

    @GetMapping("/{r}/{id}")
    public Menu get(@PathVariable int id, @NotNull @PathVariable("r") Integer restaurantId) {
        return menuService.get(id,restaurantId);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestBody MenuTo menuTo) {
        Menu created = menuService.create(menuTo, menuTo.getRestaurant().getId());
        URI uriOfResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(uriOfResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody MenuTo menuTo, @PathVariable int id) {
        assureIdConsistent(menuTo, id);
        menuService.update(menuTo, menuTo.getRestaurant().getId());
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        menuService.delete(id);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addVote(@RequestBody Menu menu) {
        super.addVote(menu);
    }
}
