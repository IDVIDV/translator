package ru.example.translator.repository;

import org.springframework.stereotype.Repository;
import ru.example.translator.entity.Language;

@Repository
public interface LanguageRepository {
    Language findById(Integer id);
    Language findByName(String name);
}
