package club.mecn.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/2/1.
 * 加密工具类
 */
public class EncryptionUtil {


    
    /**
     * 加密算法
     */
    public static final String hashType = "SHA";


    /**
     * 返回加密的密码
     * @param source
     * @return
     */
    public static String getHash(String source)
    {
        StringBuilder sb = new StringBuilder();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(hashType);
            md.update(source.getBytes());
            for (byte b : md.digest()) {
                //不足两位前面补0
                sb.append(String.format("%02X", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }




}
