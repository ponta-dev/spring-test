package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import spring.dto.SignupForm;
import spring.dto.User;
import spring.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("contents","login/home::home_contents");
		return "login/homeLayout";
	}
	
	@PostMapping("/logout")
	public String postLogout() {
		return "redirect:/login";
	}
	
	@GetMapping("/userList")
	public String getuserList(Model model) {
		model.addAttribute("contents", "login/userList::userList_contents");

		int count = userService.count();
		model.addAttribute("userListCount", count);
		
		List<User> userList = userService.selectMany();
		model.addAttribute("userList", userList);
		
		return "login/homeLayout";
	}
	
	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(
			@ModelAttribute SignupForm form,
			Model model,
			@PathVariable("id") String userId
			) {
		System.out.println("userId = " + userId);
		model.addAttribute("contents","login/userDetail::userDetail_contents");
		model.addAttribute("radioMarriage", userService.getRadioMarriage());
		if( userId != null && userId.length() > 0) {
			User user = userService.selectOne(userId);
			
			form.setUserId(user.getUserId());
			form.setUserName(user.getUserName());
			form.setPassword(user.getPassword());
			form.setBirthday(user.getBirthday());
			form.setAge(user.getAge());
			form.setMarriage(user.isMarriage());
			model.addAttribute("signupForm", form);
		}
		return "login/homeLayout";
	}
	
	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(
			@ModelAttribute SignupForm form,
			Model model
			) {
		System.out.println("update :" + form);
		boolean result = userService.updateOne(userService.getGeneralUser(form));
		if(result) {
			model.addAttribute("result", "更新成功");
		} else {
			model.addAttribute("result", "更新失敗");
		}
		return getuserList(model);
	}
	
	@PostMapping(value = "/userDetail", params = "delete")
	public String postUserDetailDelete(
			@ModelAttribute SignupForm form,
			Model model
			) {
		System.out.println("delete :" + form);
		boolean result = userService.deleteOne(form.getUserId());
		if(result) {
			model.addAttribute("result", "削除成功");
		} else {
			model.addAttribute("result", "削除失敗");
		}
		return getuserList(model);
	}
	
	@GetMapping("/userList/csv")
	public String getUserListCsv(Model model) {
		return getuserList(model);
	}
}
