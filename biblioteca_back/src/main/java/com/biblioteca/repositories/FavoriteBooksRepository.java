package com.biblioteca.repositories;

import com.biblioteca.enums.BookStatus;
import com.biblioteca.enums.FavoriteStatus;
import com.biblioteca.models.FavoriteBooks;
import com.biblioteca.models.UserBooks;
import com.biblioteca.repositories.interfaces.IFavoriteBooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class FavoriteBooksRepository implements IFavoriteBooksRepository {
    private NamedParameterJdbcTemplate jdbcTemplateNamed;
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<FavoriteBooks> getFavorites(Integer userId) {
        String sql = "SELECT * FROM FAVORITE_BOOKS WHERE USERID= :userId AND STATUS= 1;";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return jdbcTemplateNamed.query(sql, mapParams, rowMapper());
    }

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

    private RowMapper<FavoriteBooks> rowMapper(){
        return (rs, rowNum) -> new FavoriteBooks(
                rs.getInt("id"),
                rs.getInt("bookId"),
                rs.getInt("userId"),
                FavoriteStatus.fromInt(rs.getInt("status")),
                rs.getDate("addition_at")
        );
    }
}
