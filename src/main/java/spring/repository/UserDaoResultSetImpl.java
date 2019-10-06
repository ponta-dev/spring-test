package spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.dto.User;

@Repository("UserDaoResultSetImpl")
public class UserDaoResultSetImpl extends UserDaoImpl {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<User> selectMany() throws DataAccessException {
		String sql = "select * from m_user";
		UserResultSetExtractor extractor = new UserResultSetExtractor();
		return jdbc.query(sql, extractor);
	}
}
