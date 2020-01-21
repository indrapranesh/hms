package global.coda.hms.controller;

import global.coda.hms.model.Account;
import global.coda.hms.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/jwt")


public class JwtController {

    @Autowired
    JwtService jwtService;

    @PostMapping("/")
    public String login(@RequestBody Account accountDetails) throws UnsupportedEncodingException {
        return jwtService.login(accountDetails);
    }
}
