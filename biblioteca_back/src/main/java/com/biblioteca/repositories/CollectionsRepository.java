package com.biblioteca.repositories;

import com.biblioteca.mappers.CollectionsRowMapper;
import com.biblioteca.models.Collections;
import com.biblioteca.repositories.interfaces.ICollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Objects;

@AllArgsConstructor
@Repository
public class CollectionsRepository implements ICollectionRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private CollectionsRowMapper collectionsRowMapper;

    @Override
    public List<Collections> getCollecitonsByUserId(Integer userId) {
        String sql = "SELECT * FROM COLLECTIONS WHERE USERID = :userId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return namedParameterJdbcTemplate.query(sql, mapParams, collectionsRowMapper);
    }

    @Override
    public Collections getCollecitonById(Integer collectionId) {
        String sql = "SELECT * FROM COLLECTIONS WHERE COLLECTIONID = :collectionId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("collectionId", collectionId);
        Collections result = namedParameterJdbcTemplate.queryForObject(sql, mapParams, collectionsRowMapper);
        return Objects.requireNonNull(result, "Collection not found with id: " + collectionId);
    }

    @Override
    public Collections update(Integer collectionId, Collections collections) {
        String sql = "UPDATE COLLECTIONS SET "
                + "COLLECTION_NAME = :collectionName, "
                + "DESCRIPTION = :description, "
                + "COVER = :cover "
                + "WHERE COLLECTIONID = :collectionId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("collectionId", collectionId);
        mapParams.addValue("collectionName", collections.getCollection_name());
        mapParams.addValue("description", collections.getDescription());
        mapParams.addValue("cover", collections.getCover());
        namedParameterJdbcTemplate.update(sql, mapParams);
        return collections;
    }

    @Override
    public Collections create(Collections collections) {
        Date now = new Date(System.currentTimeMillis());
        Integer collectionId = generateUniqueCollectionId();

        String sql = "INSERT INTO COLLECTIONS (COLLECTIONID, USERID, COLLECTION_NAME, COVER, UPDATED_AT, DESCRIPTION) "
                + "VALUES (:collectionId, :userId, :collection_name, :cover, :updated_at, :description);";

        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("collectionId", collectionId);
        mapParams.addValue("userId", collections.getUserId());
        mapParams.addValue("cover", collections.getCover());
        mapParams.addValue("updated_at", now);
        mapParams.addValue("collection_name", collections.getCollection_name());
        mapParams.addValue("description", collections.getDescription());

        namedParameterJdbcTemplate.update(sql, mapParams);
        return collections;
    }

    @Override
    public void delete(Integer collectionId) {
        String sql = "DELETE COLLECTIONS, COLLECTION_BOOKS"
                + " FROM COLLECTIONS"
                + " LEFT JOIN COLLECTION_BOOKS ON COLLECTIONS.COLLECTIONID = COLLECTION_BOOKS.COLLECTIONID"
                + " WHERE COLLECTIONS.COLLECTIONID = :collectionId";

        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("collectionId", collectionId);
        namedParameterJdbcTemplate.update(sql, mapParams);
    }

    private Integer generateUniqueCollectionId() {
        Integer collectionId;
        do {
            collectionId = ThreadLocalRandom.current().nextInt(1000, 10000);
        } while (isExistCollectionId(collectionId));
        return collectionId;
    }

    public boolean isExistCollectionId(Integer collectionId) {
        String sql = "SELECT EXISTS (SELECT 1 FROM COLLECTIONS WHERE COLLECTIONID = :collectionId)";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("collectionId", collectionId);
        return Boolean.TRUE.equals(namedParameterJdbcTemplate.queryForObject(sql, mapParams, Boolean.class));
    }
}
