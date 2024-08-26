package com.biblioteca.repositories;

import com.biblioteca.models.Book;
import com.biblioteca.models.User;
import com.biblioteca.repositories.interfaces.IBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        String sql=" SELECT b.id, b.bookId, b.bookName, b.author, b.bookPage, b.visibility, b.additionAt, b.publishingHouse, b.printingYear, b.language FROM BOOK b";
        return jdbcTemplate.query(sql, rowMapper());
    }

    private RowMapper<Book> rowMapper(){
        return (rs, rowNum) -> new Book(
                rs.getInt("id"),
                rs.getInt("bookId"),
                rs.getString("bookName"),
                rs.getString("author"),
                rs.getInt("bookPage"),
                rs.getInt("visibility"),
                rs.getDate("additionAt"),
                rs.getString("publishingHouse"),
                rs.getString("printingYear"),
                rs.getString("language")
        );
    }
}
