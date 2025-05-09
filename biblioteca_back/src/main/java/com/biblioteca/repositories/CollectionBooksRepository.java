package com.biblioteca.repositories;

import com.biblioteca.models.CollectionBooks;
import com.biblioteca.repositories.interfaces.ICollectionBooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@AllArgsConstructor
@Repository
public class CollectionBooksRepository implements ICollectionBooksRepository {

    private NamedParameterJdbcTemplate jdbcTemplateNamed;

    @Override
    public Integer getBookCount(Integer collectionId) {
        String sql = "SELECT COUNT(*) AS COUNT FROM COLLECTION_BOOKS WHERE COLLECTIONID = :collectionId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("collectionId", collectionId);
        return jdbcTemplateNamed.queryForObject(sql, mapParams, Integer.class);
    }

    @Override
    public void addBooksToCollection(Integer collectionId, List<Long> addedBooks) {
        String sql = "INSERT INTO COLLECTION_BOOKS (COLLECTIONID, BOOKID, ADDITION_AT) "
                + " SELECT :collectionId, :bookId, NOW() "
                + " FROM DUAL "
                + " WHERE NOT EXISTS (SELECT 1 FROM COLLECTION_BOOKS WHERE COLLECTIONID = :collectionId AND BOOKID = :bookId)";

        for (Long bookId : addedBooks) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("collectionId", collectionId);
            params.addValue("bookId", bookId);

            jdbcTemplateNamed.update(sql, params);
        }
    }

    @Override
    public void removeBook(CollectionBooks collectionsBooks) {
        String sql = "DELETE FROM COLLECTION_BOOKS WHERE COLLECTIONID = :collectionId AND BOOKID = :bookId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("collectionId", collectionsBooks.getCollectionId());
        params.addValue("bookId", collectionsBooks.getBookId());

        jdbcTemplateNamed.update(sql, params);
    }

}
