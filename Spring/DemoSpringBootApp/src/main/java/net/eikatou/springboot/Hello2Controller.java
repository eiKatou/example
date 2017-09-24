package net.eikatou.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Hello2Controller {
	
	@RequestMapping(value="/hello", method=RequestMethod.POST)
	public ModelAndView send(@RequestParam("hello_message")String msg,
			ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "Hello " + msg + " !");
		mav.addObject("hello_message", msg);
		return mav;
	}
	
}
