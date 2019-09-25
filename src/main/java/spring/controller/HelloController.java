package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.dto.Employee;
import spring.service.HelloService;

@Controller
public class HelloController {
	@Autowired
	HelloService helloService;
	
	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}
	
	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1") String text1, Model model) {
		model.addAttribute("sample", text1);
		return "helloResponse";
	}
	
	@PostMapping("/hello/db")
	public String postDbRequest(@RequestParam("employee_id") String employee_id, Model model) {
		int id = Integer.parseInt(employee_id);
		Employee employee = helloService.findOne(id);
		model.addAttribute(Employee.EMPLOYEE_ID, employee.getEmployee_id());
		model.addAttribute(Employee.EMPLOYEE_NAME, employee.getEmployee_name());
		model.addAttribute(Employee.AGE, employee.getAge());
		
		return "helloResponseDB";
	}
}
