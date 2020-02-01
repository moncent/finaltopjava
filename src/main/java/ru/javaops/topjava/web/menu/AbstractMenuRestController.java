package ru.javaops.topjava.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaops.topjava.model.Menu;
import ru.javaops.topjava.service.MenuService;
import ru.javaops.topjava.web.SecurityUtil;

import java.util.List;

public abstract class AbstractMenuRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MenuService menuService;

    public List<Menu> getToday() {
        return menuService.getByCurrentDay();
    }

    public void addVote(Menu menu) {
        int userId = SecurityUtil.authUserId();
        log.info("addVote menu={} with userId={}", menu, userId);
        menuService.doVote(menu, menu.getRestaurant().getId(), userId);
    }

}
