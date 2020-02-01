package ru.javaops.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.model.Menu;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.repository.MenuRepository;
import ru.javaops.topjava.repository.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository {

    private final CrudMenuRepository menuRepository;
    private final UserRepository userRepository;


    public MenuRepositoryImpl(CrudMenuRepository menuRepository,
                              UserRepository userRepository) {
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;

    }


    @Override
    public Menu get(int id, int restaurantId) {
        return menuRepository.findById(id).filter(menu -> menu.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    @Override
    @Transactional
    public Menu save(Menu menu, int restaurantId, Meal... meals) {
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }

        if (menu.isNew() && meals.length > 0) {
            menu.setMenuItems(Arrays.asList(meals));
        }

        return menuRepository.save(menu);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return menuRepository.delete(id) != 0;
    }

    @Override
    public List<Menu> getByDate(LocalDate date) {
        return menuRepository.getByDate(date);
    }

    @Override
    @Transactional
    public boolean saveVote(int id, int restaurantId, int userId) {
        User user = userRepository.getWithVote(userId);
        List<Menu> userHistory = user.getVotes();

        Menu userVote = this.get(id, restaurantId);
        Menu existed = userHistory.stream()
                               .filter(menu -> menu.getLunchDate().isEqual(userVote.getLunchDate()))
                               .findFirst()
                               .orElse(null);

        if (existed != null) {
            userHistory.remove(existed);
            menuRepository.subtractVote(existed.getId());
        }
        userHistory.add(userVote);
        return menuRepository.addVote(id) == 1 && userRepository.save(user) != null;
    }

}