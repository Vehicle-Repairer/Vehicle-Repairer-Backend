package shuhuai.vehiclerepairer.utils;

import org.springframework.util.DigestUtils;

public class Hashing {
    public static String getHashedString(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
    }
}
