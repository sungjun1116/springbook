package com.springbook.user.dao;

import com.springbook.AppContext;
import com.springbook.user.domain.Level;
import com.springbook.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = AppContext.class)
public class UserDaoTest {

    @Autowired
    UserDao dao;

    @Autowired
    DataSource dataSource;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        System.out.println(this);
        this.user1 = new User("sungjun", "황성준", "1@naver.com", "springno1", Level.BASIC, 1, 0);
        this.user2 = new User("leegw700", "이길원", "2@naver.com", "springno2", Level.SILVER, 55, 10);
        this.user3 = new User("bumjin", "박범진", "3@naver.com", "springno3", Level.GOLD, 100, 40);
        dao.deleteAll();
    }


    @Test
    public void count() throws SQLException {
        assertThat(dao.getCount(), is((0)));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        User user2 = dao.get(user1.getId());

        assertThat(user2.getName(), is(user1.getName()));
        assertThat(user2.getPassword(), is(user1.getPassword()));
    }

    @Test
    public void addAndGet() throws SQLException {
        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        checkSameUser(userget1, user1);

        User userget2 = dao.get(user2.getId());
        checkSameUser(userget2, user2);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }

    @Test
    public void getAll() throws SQLException {

        List<User> user0 = dao.getAll();
        assertThat(user0.size(), is(0));

        dao.add(user1); // Id: sungjun
        List<User> users1 = dao.getAll();
        assertThat(users1.size(), is(1));
        checkSameUser(user1, users1.get(0));

        dao.add(user2); // Id: leegw700
        List<User> users2 = dao.getAll();
        assertThat(users2.size(), is(2));
        checkSameUser(user1, users2.get(1));
        checkSameUser(user2, users2.get(0));

        dao.add(user3); // Id: bumjin
        List<User> users3 = dao.getAll();
        assertThat(users3.size(), is(3));
        checkSameUser(user3, users3.get(0));
        checkSameUser(user1, users3.get(2));
        checkSameUser(user2, users3.get(1));
    }

    @Test
    public void update() {

        dao.add(user1);
        dao.add(user2);

        user1.setName("오민규");
        user1.setPassword("springno6");
        user1.setLevel(Level.GOLD);
        user1.setLogin(100);
        user1.setRecommend(999);

        dao.update(user1);

        User user1update = dao.get(user1.getId());
        checkSameUser(user1, user1update);
        User user2same = dao.get(user2.getId());
        checkSameUser(user2, user2same);
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getLevel(), is(user2.getLevel()));
        assertThat(user1.getLogin(), is(user2.getLogin()));
        assertThat(user1.getRecommend(), is(user2.getRecommend()));
    }

    @Test(expected = DataAccessException.class)
    public void duplicateKey() {
        dao.add(user1);
        dao.add(user1);
    }

    @Test
    public void sqlExceptionTranslate() throws Exception {
        try {
            dao.add(user1);
            dao.add(user1);
        } catch (DuplicateKeyException exception) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);

            assertThat(set.translate(null, null, sqlEx), is(DuplicateKeyException.class));
        }
    }
}
