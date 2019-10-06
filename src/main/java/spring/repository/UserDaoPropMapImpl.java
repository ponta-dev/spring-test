package spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.dto.User;

@Repository("UserDaoPropMapImpl")
public class UserDaoPropMapImpl extends UserDaoImpl {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public User selectOne(String userId) throws DataAccessException {
		String sql = "select * from m_user where user_id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		return jdbc.queryForObject(sql, rowMapper, userId);
	}

	@Override
	public List<User> selectMany() throws DataAccessException {
		String sql = "select * from m_user";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		return jdbc.query(sql, rowMapper);
	}
	
	
}
