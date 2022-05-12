package pl.kozubek.apigamereviewapp.exception;

import pl.kozubek.apigamereviewapp.enums.AuthenticationMessageEnum;

public class UserAlreadyExistsInDatabaseException extends RuntimeException {

    public UserAlreadyExistsInDatabaseException() {
        super(AuthenticationMessageEnum.USER_ALREADY_EXISTS.getMessage());
    }
}
