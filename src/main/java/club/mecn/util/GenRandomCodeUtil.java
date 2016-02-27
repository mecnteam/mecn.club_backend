package club.mecn.util;

import java.util.Random;

/**
 * Created by Administrator on 2016/2/1.
 */
public class GenRandomCodeUtil {

    private static final String baseString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 随机生成的8位密码
     */
    private static final int passwordLength = 8;

    private static final int verifycodeLenth = 4;

    /**
     * 生成随机密码(用户重置密码时)
     * @return
     */
    public static String genRandomPassword()
    {

        return genRandomCode(passwordLength);
    }

    /**
     * 生成验证码
     * @return
     */
    public static String genRandomVerifyCode()
    {
        return genRandomCode(verifycodeLenth);
    }


    private static String genRandomCode(int length)
    {
        StringBuilder buffer = new StringBuilder(baseString);

        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        int range = baseString.length();
        for(int i = 0; i < length;i++)
        {
            sb.append(buffer.charAt(r.nextInt(range)));
        }

        return sb.toString();
    }
}
