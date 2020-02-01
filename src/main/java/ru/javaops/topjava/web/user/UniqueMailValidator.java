package ru.javaops.topjava.web.user;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.javaops.topjava.HasEmail;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.repository.UserRepository;


@Component
public class UniqueMailValidator implements org.springframework.validation.Validator {

    private final UserRepository repository;

    public UniqueMailValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return HasEmail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HasEmail user = ((HasEmail) target);
        User dbUser = repository.getByEmail(user.getEmail().toLowerCase());
        if (dbUser != null && !dbUser.getId().equals(user.getId())) {
            errors.rejectValue("email", "DUPLICATE_EMAIL");
        }
    }
}
