package club.mecn.extend;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.google.code.kaptcha.util.Config;

public class KaptchaMecnExtend extends KaptchaExtend{

	private Properties props = new Properties();

	private Producer kaptchaProducer = null;

	private String sessionKeyValue = null;

	private String sessionKeyDateValue = null;
	
	private String[] fontColorRandomArray = new String[]{
			"black","red","quartz","orchid","orange","midnightblue","chocolate",
			"palevioletred"
	};
	
	
	public KaptchaMecnExtend()
	{
		ImageIO.setUseCache(false);
		Random fontIndexRan = new Random();
		int ranindex = fontIndexRan.nextInt(this.fontColorRandomArray.length);
		
		this.props.put("kaptcha.border", "no");
		this.props.put("kaptcha.textproducer.font.color", fontColorRandomArray[ranindex]);
		this.props.put("kaptcha.textproducer.char.space", "5");

		Config config = new Config(this.props);
		this.kaptchaProducer = config.getProducerImpl();
		this.sessionKeyValue = config.getSessionKey();
		this.sessionKeyDateValue = config.getSessionDate();
	}

	
}
