package com.biblioteca.repositories;

import com.biblioteca.repositories.interfaces.IFavoriteBooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class FavoriteBooksRepository implements IFavoriteBooksRepository {
    private NamedParameterJdbcTemplate jdbcTemplateNamed;

    private static final int FAVORITE_STATUS_ACTIVE = 1;
    private static final int FAVORITE_STATUS_INACTIVE = 0;

    @Override
    public void remove(Integer userId, Integer bookId) {
        String sql = "UPDATE FAVORITE_BOOKS SET STATUS = " + FAVORITE_STATUS_INACTIVE +
                " WHERE USERID = :userId AND BOOKID = :bookId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        mapParams.addValue("bookId", bookId);
        jdbcTemplateNamed.update(sql, mapParams);
    }

    @Override
    public void addBook(Integer userId, Integer bookId) {
        String sql = "INSERT INTO FAVORITE_BOOKS (USERID, BOOKID, STATUS, ADDITION_AT) " +
                "VALUES (:userId, :bookId, " + FAVORITE_STATUS_ACTIVE + ", NOW()) " +
                "ON DUPLICATE KEY UPDATE STATUS = " + FAVORITE_STATUS_ACTIVE + ", ADDITION_AT = NOW()";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        mapParams.addValue("bookId", bookId);
        jdbcTemplateNamed.update(sql, mapParams);
    }

    @Override
    public boolean isFavorite(Integer bookId, Integer userId) {
        String sql = "SELECT CASE WHEN COUNT(*) = 0 THEN 0 ELSE MAX(STATUS) END AS STATUS " +
                "FROM FAVORITE_BOOKS WHERE BOOKID = :bookId AND USERID = :userId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        mapParams.addValue("bookId", bookId);
        Integer status = jdbcTemplateNamed.queryForObject(sql, mapParams, Integer.class);
        return status != null && status == FAVORITE_STATUS_ACTIVE;
    }
}
