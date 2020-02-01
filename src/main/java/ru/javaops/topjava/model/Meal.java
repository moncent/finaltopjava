package ru.javaops.topjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import ru.javaops.topjava.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "lunch_meals")
public class Meal extends BaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Range(min = 0)
    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;


    @ManyToMany(targetEntity = Menu.class, cascade = CascadeType.MERGE)
    @JoinTable (name="menu_item",
            joinColumns=@JoinColumn (name="meals_id", referencedColumnName = "id"),
            inverseJoinColumns=@JoinColumn(name="lunch_menu_id", referencedColumnName = "id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Menu> menus;


    public Meal(Integer id, String name, int price, Restaurant restaurant) {
        super(id);
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Meal(Meal meal) {
        this(meal.getId(), meal.getName(), meal.getPrice(), meal.getRestaurant());
    }

    public Meal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", restaurant='" + restaurant + '\'' +
                ", price=" + price +
                '}';
    }
}