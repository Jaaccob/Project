package pl.kozubek.apigamereviewapp.exception;

import pl.kozubek.apigamereviewapp.enums.AuthenticationMessageEnum;

public class InvalidNickOrPassword extends RuntimeException {
    public InvalidNickOrPassword() {
        super(AuthenticationMessageEnum.INVALID_NICK_OR_PASSWORD.getMessage());
    }
}
