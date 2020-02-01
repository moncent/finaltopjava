package ru.javaops.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.Meal;


@Transactional(readOnly = true)
public interface CrudMealsRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Meal m WHERE m.id=?1")
    int delete(int id);

}