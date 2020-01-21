package global.coda.hms.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import global.coda.hms.mapper.UserMapper;
import global.coda.hms.model.Account;
import com.auth0.jwt.exceptions.JWTCreationException;
import global.coda.hms.model.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
@Service
public class JwtService {
    @Autowired
    UserMapper userMapper;

    public String login(Account accountDetails) throws UnsupportedEncodingException {
        UserDetails user = userMapper.login(accountDetails);
        try{
            int id = user.getUserId();
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create().withJWTId(String.valueOf(id))
                    .withIssuer("auth0")
                    .sign(algorithm);
        }catch(JWTCreationException e){
            throw e;
        }
    }
}
