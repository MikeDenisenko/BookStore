package com.mike.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.mike.config.HibernateConfig;
import com.mike.model.Role;
import com.mike.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={HibernateConfig.class}, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    @DatabaseSetup(value = "classpath:com/mike/model/user/user-data.xml")
    @ExpectedDatabase(value = "classpath:com/mike/model/user/user-data-create.xml",
            table = "users", assertionMode= DatabaseAssertionMode.NON_STRICT)
    public void testCreate() throws Exception {

        Role role = new Role();
        role.setTitle("admin");
        role.setId(2);
        User user = new User();
        user.setName("admin");
        user.setPassword("password");
        user.setEmail("email");
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1971-06-28"));
        user.setGender("M");
        user.setRole(role);
        userService.save(user);
    }

    @Test
    @DatabaseSetup("classpath:com/mike/model/user/user-data.xml")
    public void testGetAll() {

        ArrayList<User> actualData = new ArrayList<>(userService.findAll());

        Assert.assertEquals("user", actualData.get(0).getName());
        Assert.assertEquals("admin", actualData.get(1).getName());
    }

    @Test
    @DatabaseSetup("classpath:com/mike/model/user/user-data.xml")
    public void testFind() throws Exception {

        User actualData = userService.find(2);
        Assert.assertEquals(2, actualData.getId());
        Assert.assertEquals("admin", actualData.getName());
        Assert.assertEquals("password", actualData.getPassword());
        Assert.assertEquals("email", actualData.getEmail());
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("1971-06-28"), actualData.getBirthday());
        Assert.assertEquals("M", actualData.getGender());
    }

    @Test
    @DatabaseSetup("classpath:com/mike/model/user/user-data.xml")
    @ExpectedDatabase(value = "classpath:com/mike/model/user/user-data-update.xml",
            table = "users", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdate() {

        Role role = new Role();
        role.setTitle("admin");
        role.setId(2);

        User user = userService.find(1);
        user.setName("admin");
        user.setRole(role);
        userService.update(user);
        userService.findAll();
    }

    @Test
    @DatabaseSetup("classpath:com/mike/model/user/user-data.xml")
    @ExpectedDatabase(value = "classpath:com/mike/model/user/user-data-delete.xml",
            table = "users", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDelete(){

        userService.delete(2);
        userService.findAll();
    }
}