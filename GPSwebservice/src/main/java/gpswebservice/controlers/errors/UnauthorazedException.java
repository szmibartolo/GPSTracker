package gpswebservice.controlers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Not authorize - wrong token")  // 403
public class UnauthorazedException extends RuntimeException {
}
