package pl.kozubek.apigamereviewapp.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private String errorCode;
    private String errorDescription;
}
