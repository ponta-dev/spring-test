package spring.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import spring.dto.User;

@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public int count() throws DataAccessException {
		int count = jdbc.queryForObject(
				"select count(*) from m_user"
				, Integer.class);
		return count;
	}

	@Override
	public int insertOne(User user) throws DataAccessException {
		int rownum = jdbc.update("insert into m_user("
				+ "user_id,"
				+ "password,"
				+ "user_name,"
				+ "birthday,"
				+ "age,"
				+ "marriage,"
				+ "role"
				+ ") values (?,?,?,?,?,?,?)"
				, user.getUserId()
				, passwordEncoder.encode(user.getPassword())
				//, user.getPassword()
				, user.getUserName()
				, user.getBirthday()
				, user.getAge()
				, user.isMarriage()
				, user.getRole());
		return rownum;
	}

	@Override
	public User selectOne(String userId) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap(
				"select * from m_user"
				+ " where user_id = ?",
				userId
				);
		return convertMap2User(map);
	}

	@Override
	public List<User> selectMany() throws DataAccessException {
		List<Map<String, Object>> list = jdbc.queryForList(
				"select * from m_user"
				);
		List<User> userList = new ArrayList<User>();
		for(Map<String, Object>map : list) {
			userList.add(convertMap2User(map));
		}
		return userList;
	}

	@Override
	public int updateOne(User user) throws DataAccessException {
		int rowNumber = jdbc.update(
				"update m_user set"
				+ " password = ?,"
				+ " user_name = ?,"
				+ " birthday = ?,"
				+ " age = ?,"
				+ " marriage = ?"
				+ " where user_id = ?"
				,passwordEncoder.encode(user.getPassword())
				//,user.getPassword()
				,user.getUserName()
				,user.getBirthday()
				,user.getAge()
				,user.isMarriage()
				,user.getUserId()
				);
		return rowNumber;
	}

	@Override
	public int deleteOne(String userId) throws DataAccessException {
		int rowNumber = jdbc.update(
				"delete from m_user where user_id = ?"
				, userId
				);
		return rowNumber;
	}

	@Override
	public void userCsvOut() throws DataAccessException {
		String sql = "select * from m_user";
		
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		jdbc.query(sql, handler);
	}
	
	private User convertMap2User(Map<String, Object> map) {
		User user = new User();
		user.setUserId((String) map.get("user_id"));
		user.setPassword((String) map.get("password"));
		user.setUserName((String) map.get("user_name"));
		user.setBirthday((Date) map.get("birthday"));
		user.setAge((Integer) map.get("age"));
		user.setMarriage((Boolean) map.get("marriage"));
		user.setRole((String) map.get("role"));
		return user;
	}

}
