package com.biblioteca.repositories;

import com.biblioteca.models.User;
import com.biblioteca.repositories.interfaces.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository {

    private NamedParameterJdbcTemplate jdbcTemplateNamed;

    @Override
    public User getUserByUserId(Integer userId) {
        String query = "SELECT * FROM USER WHERE USERID = :userId ";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return jdbcTemplateNamed.queryForObject(query, mapParams, rowMapper());
    }

    @Override
    public User update(Integer userId, User user) {
        String sql= "UPDATE USER SET username = :username, name = :name, surname = :surname, " +
                "email = :email, tel_no = :tel_no, country = :country, city = :city, profile_image = :profile_image, " +
                "birth_date = :birth_date WHERE userId = :userId";

        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId",userId);
        mapParams.addValue("username", user.getUsername());
        mapParams.addValue("name", user.getName());
        mapParams.addValue("surname", user.getSurname());
        mapParams.addValue("email", user.getEmail());
        mapParams.addValue("tel_no", user.getTel_no());
        mapParams.addValue("country", user.getCountry());
        mapParams.addValue("city", user.getCity());
        mapParams.addValue("birth_date", user.getBirth_date());
        mapParams.addValue("profile_image", user.getProfile_image());


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
                rs.getString("tel_no"),
                rs.getString("country"),
                rs.getString("city"),
                rs.getDate("birth_date"),
                rs.getString("profile_image")
                );
    }
}
