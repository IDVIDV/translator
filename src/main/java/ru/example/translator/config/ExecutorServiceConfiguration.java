package ru.example.translator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorServiceConfiguration {
    @Value("${executor.service.thread.count}")
    private int threadCount;
    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(threadCount);
    }
}
