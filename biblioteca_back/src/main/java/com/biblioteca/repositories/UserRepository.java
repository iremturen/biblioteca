package com.biblioteca.repositories;

import com.biblioteca.models.User;
import com.biblioteca.repositories.interfaces.IUserRepository;
import com.biblioteca.requests.UserRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository {

    private NamedParameterJdbcTemplate jdbcTemplateNamed;
    private PasswordEncoder passwordEncoder;

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

    @Override
    public List<String> register(UserRegisterRequest request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Integer userId = generateUniqueUserId();

        String userLoginSql = "INSERT INTO user_login (username, password) VALUES (:username, :password)";
        MapSqlParameterSource userLoginParams = new MapSqlParameterSource();
        userLoginParams.addValue("username", request.getUsername());
        userLoginParams.addValue("password", hashedPassword);

        jdbcTemplateNamed.update(userLoginSql, userLoginParams);

        String userSql = "INSERT INTO user (userId, username, name, surname, email, tel_no, country, city, " +
                "birth_date) VALUES (:userId, :username, :name, :surname, :email, :tel_no, " +
                ":country, :city, :birth_date)";

        MapSqlParameterSource userParams = new MapSqlParameterSource();
        userParams.addValue("userId", userId);
        userParams.addValue("username", request.getUsername());
        userParams.addValue("name", request.getName());
        userParams.addValue("surname", request.getSurname());
        userParams.addValue("email", request.getEmail());
        userParams.addValue("tel_no", request.getTelNo());
        userParams.addValue("country", request.getCountry());
        userParams.addValue("city", request.getCity());
        userParams.addValue("birth_date", request.getBirthDate());

        jdbcTemplateNamed.update(userSql, userParams);

        return List.of("User successfully registered");
    }

    @Override
    public Integer findUserIdByUsername(String username) {
        String sql="SELECT USERID FROM USER where username=:username";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        return jdbcTemplateNamed.queryForObject(sql, params, Integer.class);    }

    private Integer generateUniqueUserId() {
        Random random = new Random();
        Integer userId;

        String checkSql = "SELECT COUNT(*) FROM user WHERE userId = :userId";
        MapSqlParameterSource params = new MapSqlParameterSource();

        do {
            userId = 1000 + random.nextInt(90000);
            params.addValue("userId", userId);
        } while (jdbcTemplateNamed.queryForObject(checkSql, params, Integer.class) > 0);

        return userId;
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
