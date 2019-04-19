package kr.ac.jejunu.userdao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {

    private UserDao userDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean(UserDao.class);
    }

    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        // user 만들고
        String name = "양소희";
        String password = "aaaa";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        // 넣고
        Long id = userDao.add(user);

        // 걔 get 해봐서
        User result = userDao.get(id);

        //확인
        assertThat(result.getId(), is(id));
        assertThat(result.getName(), is(user.getName()));
        assertThat(result.getPassword(), is(user.getPassword()));
    }

    @Test
    public void testUpate() throws SQLException, ClassNotFoundException {
        // 넣을 애 만들고
        String name = "홍길동";
        String password = "ddd";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        // 넣고
        Long id = userDao.add(user);
        user.setId(id);

        // 수정본 만들고
        String newName = "박철수";
        String newPassword = "131";
        user.setName(newName);
        user.setPassword(newPassword);

        // update 해보고
        userDao.update(user);

        // 걔 가져와서
        User result = userDao.get(id);

        // 확인
        assertThat(result.getId(), is(id));
        assertThat(result.getName(), is(newName));
        assertThat(result.getPassword(), is(newPassword));
    }

    @Test
    public void testDelete() throws SQLException, ClassNotFoundException {
        // 넣을 애 만들고
        String name = "홍길동";
        String password = "ddd";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        // 넣고
        Long id = userDao.add(user);
        user.setId(id);

        // 걔 삭제하고
        userDao.delete(id);

        // 가져와서
        User result = userDao.get(id);

        // nullValue 확인
        assertThat(result, nullValue());
    }

}
