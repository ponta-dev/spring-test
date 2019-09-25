package spring.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.constant.RoleConst;
import spring.dto.SignupForm;
import spring.dto.User;
import spring.repository.UserDao;

@Service
public class UserService {
	@Autowired
	UserDao dao;
	
	public boolean insert(User user) {
		int rownum = dao.insertOne(user);
		if(rownum > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateOne(User user) {
		int result = dao.updateOne(user);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	public int count() {
		return dao.count();
	}
	
	public List<User> selectMany() {
		return dao.selectMany();
	}
	
	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}
	
	public boolean deleteOne(String userId) {
		int result = dao.deleteOne(userId);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	public Map<String, String> getRadioMarriage() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		return radio;
	}
	
	public User getGeneralUser(SignupForm form) {
		User user = new User();
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
		user.setRole(RoleConst.ROLE_GENERAL);
		
		return user;
	}
}
