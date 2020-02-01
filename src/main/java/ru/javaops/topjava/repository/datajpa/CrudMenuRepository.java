package ru.javaops.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.Menu;

import java.time.LocalDate;
import java.util.List;


@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Menu m WHERE m.id=?1")
    int delete(int id);

    @EntityGraph(attributePaths = {"menuItems"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m ORDER BY m.lunchDate DESC")
    List<Menu> getAll();

    @EntityGraph(attributePaths = {"menuItems"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.lunchDate=:date ORDER BY m.voteCounter DESC")
    List<Menu> getByDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Menu m SET m.voteCounter=m.voteCounter+1 WHERE m.id=?1")
    int addVote(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Menu m SET m.voteCounter=m.voteCounter-1 WHERE m.id=?1")
    int subtractVote(int id);
}
