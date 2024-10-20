package com.biblioteca.repositories;

import com.biblioteca.enums.BookStatus;
import com.biblioteca.models.Book;
import com.biblioteca.models.UserBooks;
import com.biblioteca.repositories.interfaces.IUserBooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserBooksRepository implements IUserBooksRepository {

    private NamedParameterJdbcTemplate jdbcTemplateNamed;
    @Override
    public List<UserBooks> getNowReading(Integer userId) {
        String sql = "SELECT * FROM USER_BOOKS ub JOIN BOOK b ON ub.BOOKID = b.BOOKID WHERE ub.USERID = :userId AND ub.STATUS = 'NOW_READING';";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return jdbcTemplateNamed.query(sql, mapParams, rowMapper());
    }

    @Override
    public Integer getCountNowReading(Integer userId) {
        String sql = "SELECT COUNT(*) as COUNT FROM USER_BOOKS ub JOIN BOOK b ON ub.BOOKID = b.BOOKID WHERE ub.USERID = :userId AND ub.STATUS = 'NOW_READING';";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return jdbcTemplateNamed.queryForObject(sql, mapParams, Integer.class);
    }

    @Override
    public List<UserBooks> getWillRead(Integer userId) {
        String sql = "SELECT * FROM USER_BOOKS ub JOIN BOOK b ON ub.BOOKID = b.BOOKID WHERE ub.USERID = :userId AND ub.STATUS = 'WILL_READ';";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return jdbcTemplateNamed.query(sql, mapParams, rowMapper());
    }

    @Override
    public Integer getCountWillRead(Integer userId) {
        String sql = "SELECT COUNT(*) as COUNT FROM USER_BOOKS ub JOIN BOOK b ON ub.BOOKID = b.BOOKID WHERE ub.USERID = :userId AND ub.STATUS = 'WILL_READ';";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return jdbcTemplateNamed.queryForObject(sql, mapParams, Integer.class);
    }

    @Override
    public List<UserBooks> getFinished(Integer userId) {
        String sql = "SELECT * FROM USER_BOOKS ub JOIN BOOK b ON ub.BOOKID = b.BOOKID WHERE ub.USERID = :userId AND ub.STATUS = 'FINISHED';";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return jdbcTemplateNamed.query(sql, mapParams, rowMapper());
    }

    @Override
    public Integer getCountFinished(Integer userId) {
        String sql = "SELECT COUNT(*) as COUNT FROM USER_BOOKS ub JOIN BOOK b ON ub.BOOKID = b.BOOKID WHERE ub.USERID = :userId AND ub.STATUS = 'FINISHED';";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return jdbcTemplateNamed.queryForObject(sql, mapParams, Integer.class);
    }

    @Override
    public Integer updateProgress(Integer userId, Integer bookId, Integer pageNum) {
        String sql="UPDATE USER_BOOKS SET PROGRESS = :pageNum WHERE USERID = :userId AND BOOKID= :bookId;";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        mapParams.addValue("bookId", bookId);
        mapParams.addValue("pageNum", pageNum);
        return jdbcTemplateNamed.update(sql, mapParams);
    }

    private RowMapper<UserBooks> rowMapper() {
        return (rs, rowNum) -> {
            Book book = new Book(
                    rs.getInt("id"),
                    rs.getInt("bookId"),
                    rs.getString("book_name"),
                    rs.getString("author"),
                    rs.getInt("book_page"),
                    rs.getInt("visibility"),
                    rs.getDate("addition_at"),
                    rs.getString("publishing_house"),
                    rs.getString("publishing_year"),
                    rs.getString("language"),
                    rs.getString("image")
            );

            return new UserBooks(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getInt("bookId"),
                    BookStatus.valueOf(rs.getString("status")),
                    rs.getDate("updated_at"),
                    rs.getInt("is_favorite"),
                    rs.getInt("progress"),
                    book
            );
        };
    }
}
