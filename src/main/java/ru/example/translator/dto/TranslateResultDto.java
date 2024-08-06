package ru.example.translator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Результат перевода")
public class TranslateResultDto {
    @Schema(description = "Переведенный текст / Текст ошибки", example = "Hello world, this test!")
    private String output;

    @Schema(description = "Флаг, показывающий успеншость перевода", example = "true")
    private Boolean success;

    //Не знаю, зачем нужно добавлять код ответа в тело,
    //добавлено, потому что в примере ТЗ был код ответа вместе с текстом
    @Schema(description = "Код http ответа", example = "200")
    private Integer httpCode;
}
