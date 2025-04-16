package com.example.aviatickets;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class BaseIT {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:11")
            .withDatabaseName("prop")
            .withUsername("root")
            .withPassword("root")
            .withExposedPorts(5432);

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> String.format("jdbc:postgresql://localhost:%d/prop", postgres.getFirstMappedPort()));
        registry.add("spring.datasource.username", () -> "root");
        registry.add("spring.datasource.password", () -> "root");
    }
}
