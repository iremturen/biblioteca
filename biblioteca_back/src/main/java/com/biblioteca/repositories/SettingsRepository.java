package com.biblioteca.repositories;

import com.biblioteca.models.Settings;
import com.biblioteca.repositories.interfaces.ISettingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SettingsRepository implements ISettingsRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Settings> getFAQ() {
        String sql=" SELECT s.id, s.info_type, s.title, s.content FROM SETTINGS s WHERE s.info_type='FAQ'";
        return jdbcTemplate.query(sql, rowMapper());
    }

    @Override
    public List<Settings> getSupport() {
        String sql=" SELECT s.id, s.info_type, s.title, s.content FROM SETTINGS s WHERE s.info_type='SUPPORT'";
        return jdbcTemplate.query(sql, rowMapper());    }

    private RowMapper<Settings> rowMapper(){
        return (rs, rowNum) -> new Settings(
                rs.getInt("id"),
                rs.getString("info_type"),
                rs.getString("title"),
                rs.getString("content")
                );
    }
}
