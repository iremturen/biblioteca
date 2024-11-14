package com.biblioteca.repositories;


import com.biblioteca.models.Book;
import com.biblioteca.models.CollectionBooks;
import com.biblioteca.repositories.interfaces.ICollectionBooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class CollectionBooksRepository implements ICollectionBooksRepository {

    private NamedParameterJdbcTemplate jdbcTemplateNamed;
    @Override
    public Integer getBookCount(Integer collectionId) {
        String sql = "SELECT COUNT(*) as COUNT FROM COLLECTION_BOOKS WHERE COLLECTIONID= :collectionId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("collectionId", collectionId);
        return jdbcTemplateNamed.queryForObject(sql, mapParams, Integer.class);

    }

    @Override
    public void addBooksToCollection(Integer collectionId, List<Long> addedBooks) {
        String sql = "INSERT INTO collection_books (collectionId, bookId, addition_at)"
                + " SELECT :collectionId, :bookId, NOW() "
                + " FROM DUAL"
                + " WHERE NOT EXISTS (SELECT 1 FROM collection_books WHERE collectionId = :collectionId AND bookId = :bookId)";

        for (Long bookId : addedBooks) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("collectionId", collectionId);
            params.addValue("bookId", bookId);

            jdbcTemplateNamed.update(sql, params);
        }
    }

}
