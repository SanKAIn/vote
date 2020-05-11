package ru.alex.myvote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "history")
public class History extends AbstractBaseEntity {

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "vote_date")
    private LocalDate voteDate;

    @Column(name = "vote")
    private Integer vote;

    @Column(name = "menu")
    private String menu;

    public History() {
    }

    public History(Integer id, String restaurantName, LocalDate voteDate, Integer vote, String menu) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.voteDate = voteDate;
        this.vote = vote;
        this.menu = menu;
    }

    public History(String restaurantName, LocalDate voteDate, Integer vote, String menu) {
        this.id = null;
        this.restaurantName = restaurantName;
        this.voteDate = voteDate;
        this.vote = vote;
        this.menu = menu;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
