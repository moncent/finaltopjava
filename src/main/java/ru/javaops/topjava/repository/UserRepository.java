package ru.javaops.topjava.repository;

import ru.javaops.topjava.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAll();

    User get(int id);

    default User getWithVote(int id) {throw new UnsupportedOperationException();}

    User save(User user);

    User getByEmail(String email);

    boolean delete(int id);
}
