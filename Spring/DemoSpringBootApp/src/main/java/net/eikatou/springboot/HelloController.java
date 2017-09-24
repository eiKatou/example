package net.eikatou.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		logger.info("Called index.");
		mav.setViewName("index");
		mav.addObject("msg", "input your name:");
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView send(@RequestParam("name")String name,
			ModelAndView mav) {
		logger.info("Called send.");
		logger.error("Error send.");
		mav.setViewName("index");
		mav.addObject("msg", "Hello " + name + " !");
		mav.addObject("value", name);
		return mav;
	}
}
