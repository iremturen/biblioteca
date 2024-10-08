package com.biblioteca.repositories;


import com.biblioteca.models.Book;
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
        String sql = "SELECT COUNT(*) as COUNT FROM COLLECTION_BOOKS WHERE COLLECTIONID= :collectionId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("collectionId", collectionId);
        return jdbcTemplateNamed.queryForObject(sql, mapParams, Integer.class);

    }



}
