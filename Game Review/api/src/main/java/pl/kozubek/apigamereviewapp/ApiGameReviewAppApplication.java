package pl.kozubek.apigamereviewapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ApiGameReviewAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGameReviewAppApplication.class, args);
    }

}
