package com.bajaj.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("🚀 Starting Bajaj Finserv Health Test for Varun...");

        WebClient client = WebClient.builder().build();

        try {
            // ✅ Step 1: Generate webhook (Correct endpoint)
            Map<String, Object> startResponse = client.post()
                    .uri("https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA")
                    .bodyValue(Map.of(
                            "name", "Varun Kumar",
                            "email", "varunkumanalavadi1972@gmail.com", // fixed typo
                            "regNo", "PES1UG22EC329"
                    ))
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (startResponse == null) {
                System.out.println("❌ No response from generateWebhook API");
                return;
            }

            System.out.println("✅ Webhook Response: " + startResponse);

            String webhook = (String) startResponse.get("webhook");
            String token = (String) startResponse.get("accessToken");

            if (webhook == null || token == null) {
                System.out.println("❌ Missing webhook or accessToken in response");
                return;
            }

            // ✅ Step 2: Varun's SQL (Question 1 — Highest salary not on 1st day)
            String sqlQuery = """
                    SELECT
                        p.AMOUNT AS SALARY,
                        CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME,
                        TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE,
                        d.DEPARTMENT_NAME
                    FROM PAYMENTS p
                    JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID
                    JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID
                    WHERE DAY(p.PAYMENT_TIME) != 1
                    ORDER BY p.AMOUNT DESC
                    LIMIT 1;
                    """;

            // ✅ Step 3: Submit the final SQL to webhook
            Map<String, Object> finalResponse = client.post()
                    .uri(webhook)
                    .header("Authorization", token) // ⚠️ No Bearer prefix, as per Bajaj’s instruction
                    .bodyValue(Map.of("finalQuery", sqlQuery))
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            System.out.println("✅ Final Response: " + finalResponse);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.exit(0);
    }
}
