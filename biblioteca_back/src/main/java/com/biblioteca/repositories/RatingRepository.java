package com.biblioteca.repositories;

import com.biblioteca.repositories.interfaces.IRatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class RatingRepository implements IRatingRepository {

    private NamedParameterJdbcTemplate jdbcTemplateNamed;

    @Override
    public Double getAverageRating(Integer bookId) {
        String sql = "SELECT IFNULL(ROUND(AVG(RATE), 1), 0.0) AS RATE FROM RATING WHERE BOOKID= :bookId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("bookId", bookId);
        return Objects.requireNonNullElse(jdbcTemplateNamed.queryForObject(sql, mapParams, Double.class), 0.0);
    }

    @Override
    public void saveRating(Integer bookId, Integer userId, Integer rating) {
        String sql = "INSERT INTO Rating (bookId, userId, rate) VALUES (:bookId, :userId, :rating);";

        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        mapParams.addValue("bookId", bookId);
        mapParams.addValue("rating", rating);

        boolean count = isRatingExists(userId, bookId);
        if (count) {
            updateRating(userId, bookId, rating);
        } else {
            jdbcTemplateNamed.update(sql, mapParams);
        }

    }

    private boolean isRatingExists(Integer userId, Integer bookId) {
        String checkSql = "SELECT COUNT(*) FROM Rating WHERE userId = :userId AND bookId = :bookId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("bookId", bookId);
        return Objects.requireNonNullElse(jdbcTemplateNamed.queryForObject(checkSql, params, Integer.class), 0) > 0;
    }

    public void updateRating(Integer userId, Integer bookId, Integer rating) {
        String sql = "UPDATE Rating SET rate = :rating WHERE userId = :userId AND bookId = :bookId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("bookId", bookId)
                .addValue("rating", rating);
        jdbcTemplateNamed.update(sql, params);
    }
}
