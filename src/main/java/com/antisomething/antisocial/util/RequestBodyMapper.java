package com.antisomething.antisocial.util;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class RequestBodyMapper {

    public static Map<String, String> map(HttpServletRequest request) {
        String contentType = request.getContentType();
        return switch (contentType) {
            case "application/x-www-form-urlencoded" -> formUrlEncoded(request);
            default -> throw new IllegalArgumentException("Unsupported request content type");
        };
    }

    private static Map<String, String> formUrlEncoded(HttpServletRequest request) {
        String[] params;
        try (BufferedReader reader = request.getReader()) {
            params = reader.readLine().split("&");
        } catch (IOException ex) {
            throw new RuntimeException("Failed to get BufferedReader from request");
        }

        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String[] keyValuePair = param.split("=");
            map.put(keyValuePair[0], keyValuePair[1]);
        }

        return map;
    }
}
