package ru.example.translator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TranslateResult {
    Integer id;
    Integer translateRequestId;
    String output;
    Integer outputLanguageId;
}
