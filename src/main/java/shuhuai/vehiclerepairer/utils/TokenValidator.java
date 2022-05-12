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
import shuhuai.vehiclerepairer.type.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenValidator implements HandlerInterceptor {
    @Value("${token.privateKey}")
    private String privateKey;
    @Value("${token.youngToken}")
    private Long youngToken;
    @Value("${token.oldToken}")
    private Long oldToken;
    private final static ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();
    public static Map<String, String> getUser() {
        return threadLocal.get();
    }

    public static void setUser(Map<String, String> userIdentify) {
        threadLocal.set(userIdentify);
    }

    public static void removeUser() {
        threadLocal.remove();
    }

    public String getToken(String id, Role role) {
        return JWT.create().withClaim("id", id).withClaim("role", role.getType()).withClaim("timeStamp", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(privateKey));
    }

    public Map<String, String> parseToken(String token) {
        HashMap<String, String> map = new HashMap<>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(privateKey)).build().verify(token);
        Claim id = decodedjwt.getClaim("id");
        Claim role = decodedjwt.getClaim("role");
        Claim timeStamp = decodedjwt.getClaim("timeStamp");
        map.put("id", id.asString());
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
        String id = map.get("id");
        Role role = Role.valueOf(map.get("role"));
        long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get("timeStamp"));
        if (timeOfUse >= youngToken && timeOfUse < oldToken) {
            httpServletResponse.setHeader("Authorization", "Bearer " + getToken(id, role));
        } else if (timeOfUse >= oldToken) {
            throw new TokenExpireException("token过期");
        }
        setUser(map);
        return true;
    }
}