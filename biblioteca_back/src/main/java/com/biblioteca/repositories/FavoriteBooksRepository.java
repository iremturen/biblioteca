package com.biblioteca.repositories;


import com.biblioteca.repositories.interfaces.IFavoriteBooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class FavoriteBooksRepository implements IFavoriteBooksRepository {
    private NamedParameterJdbcTemplate jdbcTemplateNamed;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void remove(Integer userId, Integer bookId) {
    String sql = "UPDATE FAVORITE_BOOKS SET status = 0 WHERE userId = :userId and bookId = :bookId;";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId",userId);
        mapParams.addValue("bookId",bookId);
        jdbcTemplateNamed.update(sql, mapParams);
    }

    @Override
    public void addBook(Integer userId, Integer bookId) {
        String sql = "INSERT INTO FAVORITE_BOOKS (userId, bookId, status, addition_at) " +
                "VALUES (:userId, :bookId, 1, NOW()) " +
                "ON DUPLICATE KEY UPDATE status = 1, addition_at = NOW();";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId",userId);
        mapParams.addValue("bookId",bookId);
        jdbcTemplateNamed.update(sql, mapParams);
    }

    @Override
    public boolean isFavorite(Integer bookId, Integer userId) {
        String sql= "SELECT CASE WHEN COUNT(*) = 0 THEN 0 ELSE MAX(status)  END AS status"
                + " FROM Favorite_Books WHERE bookId = :bookId AND userId = :userId;";

        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId",userId);
        mapParams.addValue("bookId",bookId);

        int status = jdbcTemplateNamed.queryForObject(sql, mapParams, Integer.class);

         return status == 1;
    }
}
