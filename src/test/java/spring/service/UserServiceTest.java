package spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import spring.repository.UserDao;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService service;
	
	@MockBean
	@Qualifier("UserDaoImpl")
	UserDao dao;
	
	@ParameterizedTest
	@CsvSource({
		"1, 1",
		"12, 12"
	})
	public void test_count_exp_nomalcase(int exp, int actual) {
		when(dao.count()).thenReturn(exp);
		
		assertEquals(service.count(), actual);
		System.out.printf("exp %d, actual %d\n", exp, actual);
	}
}
