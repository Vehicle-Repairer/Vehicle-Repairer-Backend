package shuhuai.vehiclerepairer.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import shuhuai.vehiclerepairer.service.excep.common.TokenExpireException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenValidator implements HandlerInterceptor {
    @Value("${token.privateKey}")
    private String privateKey;
    @Value("${token.yangToken}")
    private Long yangToken;
    @Value("${token.oldToken}")
    private Long oldToken;

    public String getToken(String account, String role) {
        return JWT.create().withClaim("account", account).withClaim("role", role).withClaim("timeStamp", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(privateKey));
    }

    public Map<String, String> parseToken(String token) {
        HashMap<String, String> map = new HashMap<>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(privateKey)).build().verify(token);
        Claim account = decodedjwt.getClaim("account");
        Claim role = decodedjwt.getClaim("role");
        Claim timeStamp = decodedjwt.getClaim("timeStamp");
        map.put("account", account.asString());
        map.put("role", role.asString());
        map.put("timeStamp", timeStamp.asLong().toString());
        return map;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull Object object) {
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        String token = httpServletRequest.getHeader("Authorization");
        if (null == token || "".equals(token.trim())) {
            throw new TokenExpireException("token无效");
        }
        token = token.split(" ")[1];
        Map<String, String> map = parseToken(token);
        String account = map.get("account");
        String role = map.get("role");
        long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get("timeStamp"));
        if (timeOfUse >= yangToken && timeOfUse < oldToken) {
            httpServletResponse.setHeader("Authorization", "Bearer " + getToken(account, role));
        } else if (timeOfUse >= oldToken) {
            throw new TokenExpireException("token过期");
        }
        if (!"Repairman".equals(role) && !"Salesman".equals(role)) {
            throw new TokenExpireException("token无效");
        }
        return true;
    }
}