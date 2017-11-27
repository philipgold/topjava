package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;

@NamedQueries(value = {
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.dateTime=:dateTime, m.description=:description, m.calories=:calories WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Meal.BY_USER_ID, query = "SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.ALL_BETWEEN_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate  AND :endDate ORDER BY m.dateTime DESC")
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class Meal extends AbstractBaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String BY_USER_ID = "Meal.getByUserId";
    public static final String ALL_SORTED = "Meal.getAllSorted";
    public static final String ALL_BETWEEN_SORTED = "Meal.getBetweenAllSorted";
    public static final String UPDATE = "Meal.update";

    @Column(name = "date_time", nullable = false, columnDefinition = "date time when an user eat")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false, columnDefinition = "description of what an user eat")
    @NotBlank
    private String description;

    @Column(name = "calories", nullable = false, columnDefinition = "calories of meal")
    @NotNull
    @Range (min = 10, max = 10000)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}