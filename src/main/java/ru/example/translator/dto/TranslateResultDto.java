package ru.example.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslateResultDto {
    private String output;
    private Boolean success;
    private Integer httpCode;
}
