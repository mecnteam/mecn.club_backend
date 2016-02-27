package club.mecn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

	@RequestMapping(value = "/login" ,method = RequestMethod.GET)
	@ResponseBody
	public String login()
	{
		return "Hello Guy";
	}
	
}
