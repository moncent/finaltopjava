package ru.javaops.topjava.web.menu;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava.model.Menu;

import java.util.List;

@RestController
@RequestMapping(value = MenuProfileRestController.REST_URL)
public class MenuProfileRestController extends AbstractMenuRestController {
    static final String REST_URL = "/rest/profile/menu";

    @GetMapping(value = "/now", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getToday() {
        return super.getToday();
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addVote(@RequestBody Menu menu) {
        super.addVote(menu);
    }

}
