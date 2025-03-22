package com.biblioteca.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.biblioteca.models.Collections;

@Component
public class CollectionsRowMapper implements RowMapper<Collections> {
    @Override
    public Collections mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new Collections(
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