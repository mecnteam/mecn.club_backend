package club.mecn.util;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import club.mecn.bean.TokenInfo;
import club.mecn.module.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class TokenUtil {
	
	
	public static final Key currentKey = MacProvider.generateKey();
	
	
	/**
	 * 生成token和过期日期,用户信息
	 * @param user 用户对象
	 * @return
	 */
	public static TokenInfo genTokenJson(User user)
	{
		Date expires = genExpiredDate();
		String token = Jwts.builder().setSubject(user.getUsername())
									 .setExpiration(expires)
									 .setIssuer(user.getUserId() + "")
									 .signWith(SignatureAlgorithm.HS512, currentKey).compact();
		
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setToken(token);
		tokenInfo.setExpires(expires);
		tokenInfo.setUser(user);
		
		return tokenInfo;
	}
	
	/**
	 * 生成过期时间(7天后)
	 * @return
	 */
	private static Date genExpiredDate()
	{
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 7);
		return c.getTime();
	}

}
