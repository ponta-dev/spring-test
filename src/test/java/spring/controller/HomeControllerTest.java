package spring.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import spring.dto.Employee;
import spring.service.HelloService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = spring.controller.HelloController.class, secure = false)
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HelloService service;
	
	@Test
	//@WithMockUser
	public void sample1() throws Exception {
		Employee mock = new Employee();
		mock.setEmployee_id(1);
		mock.setEmployee_name("たけお");
		mock.setAge(12);
		when(service.findOne(1)).thenReturn(mock);
		
		mockMvc.perform(post("/hello/db").param("employee_id", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("たけお")));
	}
}
