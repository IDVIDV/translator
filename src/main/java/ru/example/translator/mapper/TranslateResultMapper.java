package ru.example.translator.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.example.translator.entity.TranslateResult;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TranslateResultMapper implements RowMapper<TranslateResult> {
    @Override
    public TranslateResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        TranslateResult translateResult = new TranslateResult();
        translateResult.setId(rs.getInt("id"));
        translateResult.setOutput(rs.getString("output"));
        translateResult.setOutputLanguageId(rs.getInt("outputLanguageId"));
        return translateResult;
    }
}
