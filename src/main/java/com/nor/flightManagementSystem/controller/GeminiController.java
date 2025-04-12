package com.nor.flightManagementSystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
public class GeminiController {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com").build();
    }

    @GetMapping("/ai/ask")
    public Mono<Map<String, String>> getGeminiResponse(@RequestParam String query) {
        if (query == null || query.trim().isEmpty()) {
            return Mono.just(Map.of("response", "Please ask a valid question."));
        }

        String url = "/v1beta/models/gemini-2.0-flash:generateContent?key=" + geminiApiKey;

        Map<String, Object> payload = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", query)))
                )
        );

        return webClient.post()
                .uri(url)
                .header("Content-Type", "application/json")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    try {
                        return Map.of("response",
                                json.at("/candidates/0/content/parts/0/text").asText("Sorry, no response."));
                    } catch (Exception e) {
                        return Map.of("response", "Sorry, could not understand the response.");
                    }
                })
                .onErrorResume(e ->
                        Mono.just(Map.of("response", "Sorry, something went wrong while calling Gemini."))
                );
    }

    @GetMapping("/aiAssistant")
    public String showAssistantPage() {
        return "aiAssistant";
    }
}