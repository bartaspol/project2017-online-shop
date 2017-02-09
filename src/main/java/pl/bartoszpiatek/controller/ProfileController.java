package pl.bartoszpiatek.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	ModelAndView home(ModelAndView modelAndView) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		modelAndView.getModel().put("message", email);
		modelAndView.setViewName("app.message");
		return modelAndView;
	}
}
