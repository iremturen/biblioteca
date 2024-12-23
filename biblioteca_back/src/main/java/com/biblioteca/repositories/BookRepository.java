package com.biblioteca.repositories;

import com.biblioteca.models.Book;
import com.biblioteca.repositories.interfaces.IBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class BookRepository implements IBookRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplateNamed;

    @Override
    public List<Book> getAllBooks(String pattern) {
        String sql=" SELECT * FROM BOOK b";

        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        if (Objects.nonNull(pattern) && !pattern.trim().isEmpty()) {
            sql += " WHERE b.BOOK_NAME LIKE :pattern OR b.AUTHOR LIKE :pattern";
            mapParams.addValue("pattern", "%" + pattern + "%");
        }
        return jdbcTemplateNamed.query(sql, mapParams, rowMapper());
    }

    @Override
    public Book getBookByBookId(Integer bookId) {
        String sql= "SELECT * FROM BOOK WHERE BOOKID = :bookId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("bookId", bookId);
        return jdbcTemplateNamed.queryForObject(sql, mapParams, rowMapper());
    }

    @Override
    public List<Book> getNewReleases() {
        String sql ="SELECT * FROM BOOK b ORDER BY ID DESC LIMIT 10";
        return jdbcTemplate.query(sql, rowMapper());
    }

    @Override
    public List<Book> getFavorites(Integer userId) {
        String sql = "SELECT fb.*, b.* FROM sys.FAVORITE_BOOKS fb JOIN sys.BOOK b ON fb.bookId = b.bookId WHERE USERID= :userId AND STATUS= 1;";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return jdbcTemplateNamed.query(sql, mapParams, rowMapper());
    }

    @Override
    public List<Book> getCollectionsBooks(Integer collectionId, String sortBy, String pattern) {
        String query ="SELECT b.* FROM sys.COLLECTION_BOOKS cb JOIN sys.BOOK b ON cb.BOOKID = b.BOOKID WHERE cb.COLLECTIONID = :collectionId";

        if (Objects.nonNull(pattern) && !pattern.trim().isEmpty()) {
            query += " AND (b.BOOK_NAME LIKE :pattern OR b.AUTHOR LIKE :pattern OR b.PUBLISHING_HOUSE LIKE :pattern)";
        }

        String orderByClause = switch (sortBy) {
            case "title" -> " ORDER BY b.BOOK_NAME";
            case "author" -> " ORDER BY b.AUTHOR";
            case "house" -> " ORDER BY b.PUBLISHING_HOUSE";
            case "page" -> " ORDER BY b.BOOK_PAGE";
            case "date" -> " ORDER BY cb.ADDITION_AT";
            default -> "";
        };
        String sql = query + orderByClause;

        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("collectionId", collectionId);

        if (Objects.nonNull(pattern) && !pattern.trim().isEmpty()) {
            mapParams.addValue("pattern", "%" + pattern + "%");
        }
        return jdbcTemplateNamed.query(sql, mapParams, rowMapper());
    }

    @Override
    public List<Book> sortBy(Integer collectionId, String sortBy, String order) {
        String sql="SELECT b.* " +
                "FROM sys.COLLECTION_BOOKS cb " +
                "JOIN sys.BOOK b ON cb.BOOKID = b.BOOKID " +
                "WHERE cb.COLLECTIONID =" + collectionId +
                " ORDER BY b."+ sortBy + " " + order;
        return jdbcTemplate.query(sql, rowMapper());
    }


    private RowMapper<Book> rowMapper(){
        return (rs, rowNum) -> new Book(
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
                rs.getString("image"),
                rs.getString("description")
        );
    }
}
