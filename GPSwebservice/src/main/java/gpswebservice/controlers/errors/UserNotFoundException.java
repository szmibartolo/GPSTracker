package gpswebservice.controlers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="User not Found or wrong user name or password")  // 404
public class UserNotFoundException extends RuntimeException{
}
