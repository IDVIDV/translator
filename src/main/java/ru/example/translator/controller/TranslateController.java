package ru.example.translator.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.example.translator.dto.TranslateRequestDto;
import ru.example.translator.dto.TranslateResultDto;
import ru.example.translator.service.TranslateService;

@RestController
@RequiredArgsConstructor
public class TranslateController {
    private final TranslateService translateService;

    @PostMapping("/translate")
    public ResponseEntity<TranslateResultDto> translate(
            HttpServletRequest request,
            @RequestBody TranslateRequestDto translateRequestDto) {
        String ip = request.getHeader("X-FORWARDED-FOR");

        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }

        translateRequestDto.setIp(ip);

        TranslateResultDto translateResultDto = translateService.translate(translateRequestDto);

        return new ResponseEntity<>(translateResultDto, HttpStatus.valueOf(translateResultDto.getHttpCode()));
    }
}
