package ru.example.translator.repository;

import org.springframework.stereotype.Repository;
import ru.example.translator.entity.TranslateResult;

@Repository
public interface TranslateResultRepository {
    TranslateResult findById(Integer id);
    void add(TranslateResult translateResult);
}
