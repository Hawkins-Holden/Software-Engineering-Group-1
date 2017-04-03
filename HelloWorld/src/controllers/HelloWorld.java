package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
/*
 * author: Crunchify.com
 * 
 */
 
@Controller
public class HelloWorld {
	
	   /* @RequestMapping(value = "/Basic", method = RequestMethod.GET)
    public ModelAndView showForm() {
    	System.out.println("it came here");
        return new ModelAndView("employeeHome", "employee", new Basic(""));
    }*/
 
	@RequestMapping(value = "/GuestBook", method = RequestMethod.POST)
	public ModelAndView helloWorld() {
		
		String message = "heellur";
		return new ModelAndView("GuestBook", "message", message);
	}
}