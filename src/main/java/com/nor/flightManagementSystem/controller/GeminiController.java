package com.nor.flightManagementSystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final com.nor.flightManagementSystem.service.FlightUserService userService;

    public GeminiController(WebClient.Builder webClientBuilder,
                            com.nor.flightManagementSystem.service.FlightUserService userService) {
        this.webClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com").build();
        this.userService = userService;
    }

    @GetMapping("/ai/ask")
    public Mono<Map<String, String>> getGeminiResponse(@RequestParam String query) {
        if (query == null || query.trim().isEmpty()) {
            return Mono.just(Map.of("response", "Please ask a valid question."));
        }

        // Retrieve current authenticated user info
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null && authentication.isAuthenticated() &&
                !"anonymousUser".equals(authentication.getName()))
                ? authentication.getName() : null;

        String role = null;
        if (username != null) {
            role = userService.getRoleByUsername(username);
        }

        // Build a user context string for AI prompt
        String userContext = "User details: " +
                (username != null ? "Username: " + username + ", " : "User not authenticated, ") +
                (role != null ? "Role: " + role + "." : "Role unknown.");

        String domainPrompt = "You are an AI assistant for AirSavvy, a Flight Management System. " +
                "Answer questions according to AirSavvy's features and restrictions. " +
                "There are two roles: Admin and Customer. " +
                "Admins can add, modify, and enquire airports, flights, and routes, and view all tickets and passengers. " +
                "Customers can only enquire about flights and routes, view their own tickets and bookings, " +
                "view passengers on their booked flights, and book flights. Customers cannot add or modify airports, flights, or routes. " +
                "User context: " + userContext + " " +
                "User question: ";

        String combinedQuery = domainPrompt + query;

        String url = "/v1beta/models/gemini-2.0-flash:generateContent?key=" + geminiApiKey;

        Map<String, Object> payload = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", combinedQuery)))
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
