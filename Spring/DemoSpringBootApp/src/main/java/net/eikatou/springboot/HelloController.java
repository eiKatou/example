package net.eikatou.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "input your name:");
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView send(@RequestParam("name")String name,
			ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "Hello " + name + " !");
		mav.addObject("value", name);
		return mav;
	}
}
