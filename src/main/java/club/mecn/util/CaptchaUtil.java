package club.mecn.util;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.Captcha.Builder;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.noise.NoiseProducer;
import nl.captcha.noise.StraightLineNoiseProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;

/**
 * 生成验证码的相关信息
 * @author vwings
 *
 */
public class CaptchaUtil {

	private static final int fontSizeMin = 30;
	
	private static final int fontSizeMax = 45;
	
	private static int genFontSize()
	{
		Random r = new Random();
		int fontSizeRange = r.nextInt(fontSizeMax - fontSizeMin + 1 );
		return fontSizeMin + fontSizeRange;
	}
	private static Color genColor()
	{
		Random ran = new Random();
		int r = ran.nextInt(256);
		int g = ran.nextInt(256);
		int b = ran.nextInt(256);
		
		return new Color(r,g,b);
	}
	private static int genLineBold()
	{
		Random r = new Random();
		int bold = r.nextInt(10);
		return bold;
	}
	private static int genNoiseNum()
	{
		Random r = new Random();
		int noiseNum = r.nextInt(6);
		return noiseNum;
	}
	private static boolean genNoiseType()
	{
		Random r = new Random();
		int num = r.nextInt(100);
		if(num > 50)
		{
			return true;
		}else
			return false;
	}
	
	public static void genCaptcha(HttpServletRequest req, HttpServletResponse resp)
	{
		Builder builder = new Builder(200, 50);
		
		for(int i=0 ; i<  CaptchaUtil.genNoiseNum();i++)
		{
			if(CaptchaUtil.genNoiseType())
			{
				
				NoiseProducer nProd = new CurvedLineNoiseProducer(genColor(),genLineBold());
				builder.addNoise(nProd);
			}else
			{
				builder.addNoise(new StraightLineNoiseProducer());
			}
		}
		
		List<Font> fontList = new ArrayList<Font>();  
        fontList.add(new Font("宋体", Font.HANGING_BASELINE,genFontSize())); 
        fontList.add(new Font("Courier", Font.ITALIC, genFontSize()));  
		
        WordRenderer wr = new DefaultWordRenderer(genColor() ,fontList);  
        
        builder.addText(wr);
        
        
        GradiatedBackgroundProducer gbp = new GradiatedBackgroundProducer();  
        gbp.setFromColor(genColor());  
        gbp.setToColor(genColor()); 
		
        
        builder.addBackground(gbp);
		
        
        Captcha captcha = builder.build();
        req.getSession().setAttribute("captcha", captcha.getAnswer());  
  
        CaptchaServletUtil.writeImage(resp, captcha.getImage());  
	}
	
	public static Map<String,Object> validateCaptcha(HttpServletRequest req,String userinputCaptcha)
	{
		if(req.getSession().getAttribute("captcha").toString().equalsIgnoreCase(userinputCaptcha))
		{
			return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS);
		}else
		{
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS);
		}
	}
	
}
