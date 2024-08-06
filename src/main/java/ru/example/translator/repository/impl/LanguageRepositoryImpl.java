package ru.example.translator.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.example.translator.entity.Language;
import ru.example.translator.mapper.LanguageMapper;
import ru.example.translator.repository.LanguageRepository;

@Repository
@RequiredArgsConstructor
public class LanguageRepositoryImpl implements LanguageRepository {
    private static final String TABLE_NAME = "\"language\"";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name FROM " + TABLE_NAME + " WHERE id = (?)";
    private static final String FIND_BY_NAME_QUERY = "SELECT id, name FROM " + TABLE_NAME + " WHERE name = (?)";

    private final LanguageMapper languageMapper;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Language findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, languageMapper, id);
    }

    @Override
    public Language findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_BY_NAME_QUERY, languageMapper, name);
    }
}
