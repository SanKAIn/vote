package ru.alex.myvote.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import org.hibernate.annotations.Cache;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id AND r.admin=:adminId"),
        @NamedQuery(name = Restaurant.ALL,  query = "SELECT r FROM Restaurant r ORDER BY r.name ASC"),
        @NamedQuery(name = Restaurant.SORT_VOTE, query = "SELECT r FROM Restaurant r ORDER BY r.vote DESC")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
    public static final String ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String SORT_VOTE = "Restaurant.getAllByVote";

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    @JsonIgnore
    @NotNull
    private User admin;*/
    @Column(name = "admin_id")
    private Integer admin;

    /*@OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @JsonIgnore
    private List<Dish> menu;*/

    @Size(min = 2, max = 5)
    @OneToMany(fetch = FetchType.EAGER, targetEntity = Dish.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<Dish> menu;

    @Column(name = "vote")
    private int vote;

    public Restaurant() {
    }

    public Restaurant(String name, List<Dish> menu, int vote){
        super(null, name);
        this.menu = menu;
        this.vote = vote;
    }

    public Restaurant(Integer id, String name, List<Dish>  menu, int vote) {
        super(id, name);
        this.menu = menu;
        this.vote = vote;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
/*public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }*/
}
