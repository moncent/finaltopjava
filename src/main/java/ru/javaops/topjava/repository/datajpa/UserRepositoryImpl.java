package ru.javaops.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.repository.UserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final CrudUserRepository crudUserRepository;


    public UserRepositoryImpl(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.getAll();
    }

    @Override
    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public User getWithVote(int id) {
        return crudUserRepository.getWithVote(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    @Transactional
    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }
}
