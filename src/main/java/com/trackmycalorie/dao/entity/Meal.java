package com.trackmycalorie.dao.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MEALS")
@NamedQueries({
        @NamedQuery(
                name = "User.getAllByUserId",
                query = "select o from Meal o where o.user.id = :userId"
        ),
})
public class Meal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    @Type(type = "timestamp")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "calories")
    private Integer calories;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Meal() {
    }

    public Meal(String description, Integer calories, Date time, User user) {
        this.description = description;
        this.calories = calories;
        this.date = time;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
