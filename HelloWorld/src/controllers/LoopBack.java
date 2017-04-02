package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class LoopBack {
	
	@RequestMapping("/foond")
	public ModelAndView helloWorld() {
		
		String message = "Hi there buckaroo";
		return new ModelAndView("welcome", "message", message);
	}

}
