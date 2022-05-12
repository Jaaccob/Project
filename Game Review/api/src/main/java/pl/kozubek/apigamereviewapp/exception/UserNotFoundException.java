package pl.kozubek.apigamereviewapp.exception;

import pl.kozubek.apigamereviewapp.enums.AuthenticationMessageEnum;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(AuthenticationMessageEnum.USER_NOT_FOUND.getMessage());
    }
}
