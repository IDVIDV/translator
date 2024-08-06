package ru.example.translator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на перевод текста")
public class TranslateRequestDto {
    @Schema(description = "IP пользователя (достается из http запроса)", example = "0.0.0.0")
    String ip;

    @Schema(description = "Язык введёного текста по стандарту ISO-639", example = "ru")
    String inputLanguage;

    @Schema(description = "Язык запрашиваемого перевода по стандарту ISO-639", example = "en")
    String outputLanguage;

    @Schema(description = "Переводимый текст", example = "Привет мир, это тест!")
    String input;
}
