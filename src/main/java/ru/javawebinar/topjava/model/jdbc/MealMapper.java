package ru.javawebinar.topjava.model.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealMapper implements RowMapper<Meal> {
    @Override
    public Meal mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Meal(
                rs.getInt("id"),
                DateTimeUtil.parseLocalDateTime(rs.getString("date_time")),
                rs.getString("description"),
                rs.getInt("calories"));
    }
}
