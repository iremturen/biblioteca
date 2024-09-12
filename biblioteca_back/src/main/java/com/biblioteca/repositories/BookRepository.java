package com.biblioteca.repositories;

import com.biblioteca.models.Book;
import com.biblioteca.models.User;
import com.biblioteca.repositories.interfaces.IBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class BookRepository implements IBookRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplateNamed;

    @Override
    public List<Book> getAllBooks() {
        String sql=" SELECT b.id, b.bookId, b.book_name, b.author, b.book_page, b.visibility, b.addition_at, b.publishing_house, b.publishing_year, b.language, b.image FROM BOOK b";
        return jdbcTemplate.query(sql, rowMapper());
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
        String sql ="SELECT * FROM BOOK b ORDER BY ID DESC LIMIT 5";
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
                rs.getString("image")
        );
    }
}
