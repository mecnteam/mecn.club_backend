package club.mecn.controller;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.servlet.KaptchaExtend;

import club.mecn.extend.KaptchaMecnExtend;
import club.mecn.util.CaptchaUtil;
import nl.captcha.Captcha;
import nl.captcha.Captcha.Builder;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.noise.NoiseProducer;
import nl.captcha.noise.StraightLineNoiseProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;

@Controller
@RequestMapping("captcha")
public class CaptchaController{

	@RequestMapping(value = "/captcha.jpg", method = RequestMethod.GET)
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.captcha(req, resp);
    }
	
	@RequestMapping(value = "/gen", method = RequestMethod.GET)
    public void genCaptcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
          CaptchaUtil.genCaptcha(req,resp);
    }
	
	@RequestMapping(value = "/validate/{captcha}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> validateCaptcha(HttpServletRequest req,@PathVariable("captcha") String captcha)
	{
		return CaptchaUtil.validateCaptcha(req,captcha);
	}
}
