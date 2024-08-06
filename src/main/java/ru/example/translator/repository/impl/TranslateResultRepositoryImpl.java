package ru.example.translator.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.example.translator.entity.TranslateResult;
import ru.example.translator.mapper.TranslateResultMapper;
import ru.example.translator.repository.TranslateResultRepository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class TranslateResultRepositoryImpl implements TranslateResultRepository {
    private static final String TABLE_NAME = "\"translate_result\"";
    private static final String FIND_BY_ID_QUERY = "SELECT id, translate_request_id, output, output_language_id FROM "
            + TABLE_NAME + " WHERE id = (?)";
    private static final String ADD_QUERY = "INSERT INTO " + TABLE_NAME + " VALUES (DEFAULT, (?), (?), (?))";

    private final TranslateResultMapper translateResultMapper;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public TranslateResult findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, translateResultMapper, id);
    }

    @Override
    public void add(TranslateResult translateResult) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, translateResult.getTranslateRequestId());
            ps.setObject(2, translateResult.getOutput());
            ps.setObject(3, translateResult.getOutputLanguageId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKeys().containsKey("id")) {
            translateResult.setId((Integer) keyHolder.getKeys().get("id"));
        }
    }
}
