package com.biblioteca.mappers;

import com.biblioteca.models.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<Book> {
    @Override
    @NonNull
    public Book mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new Book(
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