package com.biblioteca.repositories;

import com.biblioteca.models.Collections;
import com.biblioteca.repositories.interfaces.ICollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class CollectionsRepository implements ICollectionRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Collections> getCollecitonsByUserId(Integer userId) {
        String sql= "SELECT * FROM COLLECTIONS WHERE USERID = :userId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("userId", userId);
        return namedParameterJdbcTemplate.query(sql, mapParams, rowMapper());
    }

    @Override
    public Collections getCollecitonById(Integer collectionId) {
        String sql= "SELECT * FROM COLLECTIONS WHERE COLLECTIONID = :collectionId";
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("collectionId", collectionId);
        return namedParameterJdbcTemplate.queryForObject(sql, mapParams, rowMapper());
    }

    RowMapper<Collections> rowMapper(){
        return(rs, rowNum) -> new Collections(
                rs.getInt("id"),
                rs.getInt("userId"),
                rs.getInt("collectionId"),
                rs.getString("collection_name"),
                rs.getDate("updated_at"),
                rs.getInt("visibility"),
                rs.getString("cover"),
                rs.getString("description")
                );
    }
}
