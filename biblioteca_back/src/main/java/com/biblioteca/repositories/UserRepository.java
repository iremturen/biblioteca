package com.biblioteca.repositories;

import com.biblioteca.models.User;
import com.biblioteca.repositories.interfaces.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplateNamed;

    @Override
    public User getUserByUserId(int userId) {
        String query = "SELECT * FROM USERS WHERE USERID = :userId ";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return jdbcTemplateNamed.queryForObject(query, mapParams, rowMapper());
    }

    @Override
    public User update(int userId, User user) {
        String sql= "UPDATE USERS SET username = :username, name = :name, surname = :surname, " +
                "email = :email, telNo = :telNo, country = :country, city = :city, " +
                "birthDay = :birthDay WHERE userId = :userId";

        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId",userId);
        mapParams.addValue("username", user.getUsername());
        mapParams.addValue("name", user.getName());
        mapParams.addValue("surname", user.getSurname());
        mapParams.addValue("email", user.getEmail());
        mapParams.addValue("telNo", user.getTelNo());
        mapParams.addValue("country", user.getCountry());
        mapParams.addValue("city", user.getCity());
        mapParams.addValue("birthDay", user.getBirthDay());

        jdbcTemplateNamed.update(sql,mapParams);
        return getUserByUserId(userId);
    }


    private RowMapper<User> rowMapper(){
        return (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getInt("userId"),
                rs.getString("username"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("email"),
                rs.getString("telNo"),
                rs.getString("country"),
                rs.getString("city"),
                rs.getDate("birthDay")
                );
    }
}
