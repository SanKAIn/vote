package ru.alex.myvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant=:restId"),
        @NamedQuery(name = Dish.ALL_BY_REST, query = "SELECT d FROM Dish d WHERE d.restaurant=:restId ORDER BY d.name ASC")
})
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id"}, name = "dish_idx")})
public class Dish extends AbstractNamedEntity  {
    public static final String ALL_BY_REST = "Dish.getAll";
    public static final String DELETE = "Dish.delete";

    @Column(name = "cost", nullable = false)
    @NotNull
    private Integer cost;

    /*@ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @NotNull
    @JsonIgnore
    private Restaurant restaurant;*/

    @Column(name = "restaurant_id")
    private Integer restaurant;

    public Dish() {
    }

    public Dish(String name, int cost) {
        this(null ,name,cost);
    }

    public Dish(Integer id, String name, int cost){
        super(id,name);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    /*public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }*/

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                " name=" + name +
                " cost=" + cost +
                '}';
    }
}
