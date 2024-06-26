package com.antisomething.antisocial.util;

import com.antisomething.antisocial.constant.HttpContentType;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public final class RequestBodyMapper {

    public static Map<String, String> map(HttpServletRequest request) {
        String contentType = request.getContentType();
        return switch (contentType) {
            case HttpContentType.FORM_URLENCODED -> formUrlEncoded(request);
            default -> throw new IllegalArgumentException("Unsupported request content type");
        };
    }

    private static Map<String, String> formUrlEncoded(HttpServletRequest request) {
        String[] params;
        try (BufferedReader reader = request.getReader()) {
            params = URLDecoder.decode(reader.readLine(), StandardCharsets.UTF_8).split("&");
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
