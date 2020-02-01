package ru.javaops.topjava.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import ru.javaops.topjava.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lunch_menu")
public class Menu extends BaseEntity {

    @NotNull
    @Column(name = "lunch_date", nullable = false)
    private LocalDate lunchDate;

    @ManyToMany(targetEntity = Meal.class, fetch = FetchType.EAGER)
    @JoinTable (name="menu_item",
            joinColumns=@JoinColumn (name="lunch_menu_id", referencedColumnName = "id"),
            inverseJoinColumns=@JoinColumn(name="meals_id", referencedColumnName = "id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private List<Meal> menuItems;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    @Range(min = 0)
    @Column(name = "vote_count", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int voteCounter;

    @ManyToMany(targetEntity = User.class, cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_vote",
            joinColumns=@JoinColumn (name="lunch_menu_id", referencedColumnName = "id"),
            inverseJoinColumns=@JoinColumn(name="user_id", referencedColumnName = "id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> users;

   public Menu(Integer id, LocalDate lunchDate, int voteCounter, List<Meal> menuItems, Restaurant restaurant) {
        super(id);
        this.lunchDate = lunchDate;
        this.voteCounter = voteCounter;
        this.restaurant = restaurant;
        this.menuItems = menuItems;
    }

    public Menu(Integer id, LocalDate lunchDate, List<Meal> menuItems, Restaurant restaurant) {
        this(id, lunchDate, 0, menuItems, restaurant);
    }


    public Menu(Integer id, LocalDate lunchDate, Restaurant restaurant) {
        this(id, lunchDate, 0, null, restaurant);
    }

    public Menu(Menu menu) {
       this(menu.getId(), menu.getLunchDate(), menu.getVoteCounter(), menu.getMenuItems(), menu.getRestaurant());
    }

    public Menu() {
    }


    public LocalDate getLunchDate() {
        return lunchDate;
    }

    public void setLunchDate(LocalDate lunchDate) {
        this.lunchDate = lunchDate;
    }


    public List<Meal> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Meal> menuItems) {
        this.menuItems = menuItems;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getVoteCounter() {
        return voteCounter;
    }

    public void setVoteCounter(int voteCounter) {
        this.voteCounter = voteCounter;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", lunchDate=" + lunchDate +
                ", restaurant=" + restaurant +
                ", voteCounter=" + voteCounter +
                '}';
    }
}
