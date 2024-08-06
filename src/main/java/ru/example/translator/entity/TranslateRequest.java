package ru.example.translator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TranslateRequest {
    Integer id;
    String ip;
    String input;
    Integer inputLanguageId;
}
