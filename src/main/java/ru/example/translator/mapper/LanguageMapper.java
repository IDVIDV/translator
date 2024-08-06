package ru.example.translator.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.example.translator.entity.Language;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LanguageMapper implements RowMapper<Language> {

    @Override
    public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
        Language language = new Language();
        language.setId(rs.getInt("id"));
        language.setName(rs.getString("name"));
        return language;
    }
}
