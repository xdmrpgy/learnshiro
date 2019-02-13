package com.roypan.learnshiro.utils;

import com.roypan.learnshiro.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author by Roy Pan
 * 使用md5及哈希散列来对用户密码进行加密
 */
public class PasswordHelper {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    public static final String ALGORITHM_NAME = "md5"; //散列算法
    public static final int HASH_ITRATIONS = 2; //散列次数

    public static void encryptPassword(User user){
        //随机字符串作为加密盐
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(ALGORITHM_NAME,user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),HASH_ITRATIONS).toHex();
        user.setPassword(newPassword);
    }
}
