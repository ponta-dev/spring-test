package spring.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dto.Employee;
import spring.repository.HelloRepository;

@Service
public class HelloService {
	@Autowired
	private HelloRepository helloRepository;
	
	public Employee findOne(int id) {
		Map<String, Object> map =helloRepository.findOne(id);
		
		Employee employee = new Employee();
		employee.setEmployee_id((int)map.get(Employee.EMPLOYEE_ID));
		employee.setEmployee_name((String)map.get(Employee.EMPLOYEE_NAME));
		employee.setAge((int)map.get(Employee.AGE));
		
		return employee;
	}
}
