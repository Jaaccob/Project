package pl.kozubek.apigamereviewapp.controller.hendler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kozubek.apigamereviewapp.controller.dto.ErrorMessage;
import pl.kozubek.apigamereviewapp.exception.InvalidNickOrPassword;
import pl.kozubek.apigamereviewapp.exception.UserAlreadyExistsInDatabaseException;

@RestControllerAdvice
public class AuthenticationControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage nickOrPasswordIncorrectExceptionHandler(InvalidNickOrPassword exception) {
        return ErrorMessage.builder()
                .errorCode(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .errorDescription(exception.getMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage userAlreadyExistsInDatabaseExceptionHandler(UserAlreadyExistsInDatabaseException exception) {
        return ErrorMessage.builder()
                .errorCode(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .errorDescription(exception.getMessage())
                .build();
    }
}
