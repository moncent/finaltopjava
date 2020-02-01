package ru.javaops.topjava.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.service.UserService;
import ru.javaops.topjava.to.UserTo;
import ru.javaops.topjava.util.UserUtil;

import java.util.List;

import static ru.javaops.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javaops.topjava.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public List<User> getAll() {
        log.info("getAll");
        return userService.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return userService.get(id);
    }

    public User getByMail(String email) {
        log.info("getByMail email={}", email);
        return userService.getByEmail(email);
    }


    public User getWithVote(int id) {
        log.info("getWithVote {}", id);
        return userService.getWithVote(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return userService.create(user);
    }

    public User create(UserTo userTo) {
        log.info("create from to {}", userTo);
        return create(UserUtil.createNewFromTo(userTo));
    }

    public void delete(int id) {
        log.info("delete id={}", id);
        userService.delete(id);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        userService.update(userTo);
    }

    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        log.info("enable {} with id={}", enabled, id);
        userService.enable(id, enabled);
    }

}