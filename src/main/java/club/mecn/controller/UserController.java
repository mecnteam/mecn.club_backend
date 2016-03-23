package club.mecn.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import club.mecn.bean.TokenInfo;
import club.mecn.module.User;
import club.mecn.service.UserService;
import club.mecn.util.JsonUtil;
import club.mecn.util.TokenUtil;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * 用户登录
	 * @return
	 */
	@RequestMapping(value = "/login" ,method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(@RequestParam(value = "identifier",required=true) String identifier ,
			@RequestParam(value = "password",required=true) String password ,
			HttpServletRequest request)
	{
		Map<String,Object> loginResult = userService.login(identifier, password);
		TokenInfo tokeninfo = null;
		//找到这个用户返回token
		if(((Integer)loginResult.get(JsonUtil.RETURN_STATUS)) == 1)
		{
			tokeninfo = TokenUtil.genTokenJson((User)loginResult.get(JsonUtil.RETURN_DATA));
			return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS, tokeninfo);
		}else
		{
			return loginResult;
		}

	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> register(
			@RequestParam(value = "username",required = true) String username,
			@RequestParam(value = "password",required = true) String password,
			@RequestParam(value = "email",required = true) String email)
	{
		
		if(username.isEmpty() || password.isEmpty() || email.isEmpty())
		{
			return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS, "注册失败");
		}
		else
		{
			
			User user = new User(username, password, email);
			user.setActiveEmail(true);
			userService.register(user);
			return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS,"注册成功");
		}
	
	
	}
	
}
