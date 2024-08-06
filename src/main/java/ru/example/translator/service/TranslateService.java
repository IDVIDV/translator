package ru.example.translator.service;

import org.springframework.stereotype.Service;
import ru.example.translator.dto.TranslateRequestDto;
import ru.example.translator.dto.TranslateResultDto;

@Service
public interface TranslateService {
    TranslateResultDto translate(TranslateRequestDto translateRequestDto);
}
