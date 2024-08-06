package ru.example.translator.repository;

import org.springframework.stereotype.Repository;
import ru.example.translator.entity.TranslateRequest;

@Repository
public interface TranslateRequestRepository {
    void add(TranslateRequest translateRequest);
}
