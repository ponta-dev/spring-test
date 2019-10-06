package spring.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserDaoImplTest {
	
	@Autowired
	@Qualifier("UserDaoImpl")
	private UserDao dao;
	
	@Test
	@Sql("/sql/spring/repository/UserDaoImpl/test_count_exp_0count.sql")
	public void test_count_exp_0count() {
		System.out.println(dao.selectMany());
		assertEquals(dao.count(), 0);
	}
	
	@Test
	@Sql("/sql/spring/repository/UserDaoImpl/test_count_exp_2count.sql")
	public void test_count_exp_2count() {
		System.out.println(dao.selectMany());
		assertEquals(dao.count(), 2);
	}
}
