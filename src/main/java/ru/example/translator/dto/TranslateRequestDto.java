package ru.example.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslateRequestDto {
    String ip;
    String inputLanguage;
    String outputLanguage;
    String input;
}
