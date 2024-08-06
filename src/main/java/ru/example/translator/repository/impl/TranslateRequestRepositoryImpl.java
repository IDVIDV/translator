package ru.example.translator.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.example.translator.entity.TranslateRequest;
import ru.example.translator.repository.TranslateRequestRepository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class TranslateRequestRepositoryImpl implements TranslateRequestRepository {
    private static final String TABLE_NAME = "\"translate_request\"";
    private static final String ADD_QUERY = "INSERT INTO " + TABLE_NAME + " VALUES (DEFAULT, (?), (?), (?))";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void add(TranslateRequest translateRequest) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, translateRequest.getIp());
            ps.setObject(2, translateRequest.getInput());
            ps.setObject(3, translateRequest.getInputLanguageId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKeys().containsKey("id")) {
            translateRequest.setId((Integer) keyHolder.getKeys().get("id"));
        }
    }
}
