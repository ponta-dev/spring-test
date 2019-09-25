package spring.dto;

import lombok.Data;

@Data
public class Employee {
	public static String EMPLOYEE_ID ="employee_id";
	public static String EMPLOYEE_NAME ="employee_name";
	public static String AGE ="age";
	
	private int employee_id;
	private String employee_name;
	private int age;
}
