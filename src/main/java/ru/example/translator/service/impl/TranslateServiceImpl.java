package ru.example.translator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.example.translator.dto.TranslateRequestDto;
import ru.example.translator.dto.TranslateResultDto;
import ru.example.translator.entity.Language;
import ru.example.translator.entity.TranslateRequest;
import ru.example.translator.entity.TranslateResult;
import ru.example.translator.repository.LanguageRepository;
import ru.example.translator.repository.TranslateRequestRepository;
import ru.example.translator.repository.TranslateResultRepository;
import ru.example.translator.service.TranslateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class TranslateServiceImpl implements TranslateService {
    private final LanguageRepository languageRepository;
    private final TranslateRequestRepository translateRequestRepository;
    private final TranslateResultRepository translateResultRepository;

    private final RestTemplate restTemplate;
    private final ExecutorService executorService;
    @Value("${external.translator.url}")
    private String externalTranslatorUrl;

    @Override
    public TranslateResultDto translate(TranslateRequestDto translateRequestDto) {
        TranslateRequest translateRequest = new TranslateRequest(null, translateRequestDto.getIp(),
                translateRequestDto.getInput(), null);

        Language inputLanguage = languageRepository.findByName(translateRequestDto.getInputLanguage());
        Language outputLanguage = languageRepository.findByName(translateRequestDto.getOutputLanguage());

        if (inputLanguage == null || outputLanguage == null) {
            return handleUnsupportedLanguages(translateRequest, inputLanguage, outputLanguage);
        }

        return handleSupportedLanguages(translateRequest, inputLanguage, outputLanguage);
    }

    private TranslateResultDto handleUnsupportedLanguages(
            TranslateRequest translateRequest,
            Language inputLanguage, Language outputLanguage) {
        TranslateResult translateResult = new TranslateResult();
        TranslateResultDto translateResultDto = new TranslateResultDto();

        translateResultDto.setHttpCode(400);
        translateResultDto.setSuccess(false);

        if (inputLanguage == null) {
            translateResultDto.setOutput("Язык введённого текста не поддерживается");
        } else {
            translateRequest.setInputLanguageId(inputLanguage.getId());
        }

        if (outputLanguage == null) {
            String prevOutput = translateResultDto.getOutput();

            if (prevOutput == null) {
                prevOutput = "";
            }

            translateResultDto.setOutput(prevOutput + "Язык перевода не поддерживается");
        } else {
            translateResult.setOutputLanguageId(outputLanguage.getId());
        }

        translateRequestRepository.add(translateRequest);

        translateResult.setTranslateRequestId(translateRequest.getId());
        translateResultRepository.add(translateResult);

        return translateResultDto;
    }

    private TranslateResultDto handleSupportedLanguages(
            TranslateRequest translateRequest,
            Language inputLanguage,
            Language outputLanguage) {
        TranslateResultDto translateResultDto = new TranslateResultDto();

        translateRequest.setInputLanguageId(inputLanguage.getId());
        translateRequestRepository.add(translateRequest);

        TranslateResult translateResult = new TranslateResult();
        translateResult.setTranslateRequestId(translateRequest.getId());
        translateResult.setOutputLanguageId(outputLanguage.getId());

        String output = translateConcurrent(translateRequest.getInput(), inputLanguage.getName(),
                outputLanguage.getName());

        translateResult.setOutput(output);
        translateResultRepository.add(translateResult);

        if (output == null) {
            translateResultDto.setHttpCode(400);
            translateResultDto.setSuccess(false);
            translateResultDto.setOutput("Произошла ошибка внешнего сервиса-переводчика");
        } else {
            translateResultDto.setHttpCode(200);
            translateResultDto.setSuccess(true);
            translateResultDto.setOutput(output);
        }

        return translateResultDto;
    }

    private String translateConcurrent(String input, String inputLanguage, String outputLanguage) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] inputByWords = input.split(" ");

        List<Future<ResponseEntity<Map>>> outputFutures = new ArrayList<>();

        for (String word : inputByWords) {
            outputFutures.add(CompletableFuture.supplyAsync(
                    () -> restTemplate.getForEntity(externalTranslatorUrl, Map.class,
                            inputLanguage, outputLanguage, word),
                    executorService
            ));
        }

        for (Future<ResponseEntity<Map>> future : outputFutures) {
            try {
                stringBuilder.append(future.get().getBody().get("destination-text").toString()).append(" ");
            } catch (InterruptedException | ExecutionException e) {
                executorService.shutdownNow();
                return null;
            }
        }

        return stringBuilder.toString();
    }
}
