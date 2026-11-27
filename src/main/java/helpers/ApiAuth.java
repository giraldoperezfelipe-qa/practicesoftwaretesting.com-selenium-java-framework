package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.*;

import static constants.Constants.USER_LOGIN_API_PATH;
import static helpers.GetConfigProperties.getBaseUrl;

public class ApiAuth {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String getAuthToken(String email, String password) throws Exception {
        String body = MAPPER.writeValueAsString(
                java.util.Map.of("email", email, "password", password));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrl() + USER_LOGIN_API_PATH))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IllegalStateException("Login failed: " + response.statusCode());
        }
        return MAPPER.readTree(response.body()).get("access_token").asText();
    }
}