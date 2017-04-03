package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoopBack {
	
	@RequestMapping("/welcome")
	public ModelAndView loopBack(@RequestParam("fname") String fname /*, @RequestParam("lname") String lname,
			@RequestParam("stAddress") String stAddress,
			@RequestParam("city") String city, 
			@RequestParam("state") String state,
			@RequestParam("zip") String zip, 
			@RequestParam("country") String country,
			@RequestParam("party") String partyNum,
			@RequestParam("heardFrom") String heardFrom, 
			@RequestParam("destination") String destination,
			@RequestParam("whyHere") String reasonHere,
			@RequestParam("email") String email */) {
		
		//String message = ("Here is some information regarding the visitor: \n" + stAddress + "\n" + city + "\n" + state + "\n" + destination );
		String message = fname;
		
		//Add object to MVC
		
		return new ModelAndView("welcome", "message", message);
	}

}
