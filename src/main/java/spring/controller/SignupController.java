package spring.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import spring.dto.SignupForm;
import spring.service.UserService;
import spring.valid.GroupOrder;

@Controller
public class SignupController {
	
	@Autowired
	private UserService userService;
	
	private Map<String, String> radioMarriage;
	
	private Map<String, String> initRadioMarriage(){
		Map<String, String> radio = new LinkedHashMap<String, String>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		return radio;
	}
	
	@GetMapping("/signup")
	public String getSignUp(
			@ModelAttribute SignupForm form,
			Model model
			) {
		radioMarriage = initRadioMarriage();
		model.addAttribute("radioMarriage", radioMarriage);
		return "login/signup";
	}
	
	@PostMapping("/signup")
	public String postSignUp(
			@ModelAttribute @Validated(GroupOrder.class) SignupForm form,
			BindingResult bindingResult,
			Model model
			) {
		if(bindingResult.hasErrors()) {
			return getSignUp(form, model);
		}
		System.out.println(form);
		
		boolean result = userService.insert(userService.getGeneralUser(form));
		if(result) {
			System.out.println("insert success");
		} else {
			System.out.println("insert faild");
		}
		
		return "redirect:/login";
	}
}
