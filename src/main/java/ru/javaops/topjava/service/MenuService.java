package ru.javaops.topjava.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javaops.topjava.model.Menu;
import ru.javaops.topjava.repository.MenuRepository;
import ru.javaops.topjava.to.MenuTo;
import ru.javaops.topjava.util.MenuUtil;
import ru.javaops.topjava.util.exception.ExpiredVoteException;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

import static ru.javaops.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu create(Menu menu, @NotNull Integer restaurantId) {
        return menuRepository.save(menu, restaurantId);
    }

    public Menu create(MenuTo menuTo, @NotNull Integer restaurantId) {
        return menuRepository.save(MenuUtil.createFromTo(menuTo), restaurantId);
    }

    public void update(Menu menu, @NotNull Integer restaurantId) {
        Assert.notNull(menu, "menu mustn't be null!");
        checkNotFoundWithId(menuRepository.save(menu, restaurantId), menu.getId());
    }

    public void update(MenuTo menuTo, @NotNull Integer restaurantId) {
        Assert.notNull(menuTo, "menu mustn't be null!");
        checkNotFoundWithId(menuRepository.save(MenuUtil.createFromTo(menuTo), restaurantId), menuTo.getId());
    }

    @Transactional
    public void doVote(Menu menu, @NotNull Integer restaurantId, int userId) {
        Assert.notNull(menu, "menu mustn't be null!");
        Assert.notNull(get(menu.getId(), menu.getRestaurant().getId()), "invalid menu!");

        LocalDateTime now = LocalDateTime.now();
        if (menu.getLunchDate().isAfter(ChronoLocalDate.from(now.toLocalDate())) ||
                (menu.getLunchDate().isEqual(ChronoLocalDate.from(now.toLocalDate())) && now.toLocalTime().isBefore(LocalTime.of(11,0)))) {
                menuRepository.saveVote(menu.getId(), restaurantId, userId);
        } else {
            throw new ExpiredVoteException("vote is expired!");
        }
    }


    public void delete(int id) {
        checkNotFoundWithId(menuRepository.delete(id), id);
    }

    public Menu get(int id, @NotNull Integer restaurantId) {
        return checkNotFoundWithId(menuRepository.get(id, restaurantId), id);
    }

    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    public List<Menu> getByDate(LocalDate date) {
        return menuRepository.getByDate(date);
    }

    public List<Menu> getByCurrentDay() {
        return this.getByDate(LocalDate.now());
    }

}
