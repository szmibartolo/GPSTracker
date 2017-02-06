package gpswebservice.controlers;

import gpswebservice.controlers.errors.UnauthorazedException;
import gpswebservice.controlers.errors.UserNotFoundException;
import gpswebservice.ioc.Ioc;
import gpswebservice.model.Token;
import gpswebservice.storage.UserStorage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private UserStorage userStorage;

    public LoginController() {
        userStorage = Ioc.getInstance().getUserStorage();
    }

    @RequestMapping("/login")
    public Token loginUser(@RequestHeader("X-Auth-Username") String username,
                           @RequestHeader("X-Auth-Password") String password) {

        Token token = userStorage.loginUser(username, password);
        if (token == null) {
            throw new UserNotFoundException();
        }
        return token;
    }

    @RequestMapping("/logout")
    @ResponseStatus(value = HttpStatus.OK)
    public void logoutUser(@RequestHeader("X-Auth-Token") String xtoken) {

        Token token = new Token(xtoken);
        if(!userStorage.logoutUser(token)) {
            throw new UnauthorazedException();
        }
    }
}
