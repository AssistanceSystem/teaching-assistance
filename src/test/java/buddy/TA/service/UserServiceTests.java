package buddy.TA.service;

import buddy.TA.TeachingAssistanceApplication;
import buddy.TA.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TeachingAssistanceApplication.class)
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void getAllUser() {
        List<User> users = this.userService.getAllUser();
        assertEquals(2, users.size());
    }

    @Test
    public void getUser() {
        User user = this.userService.getUser(1L);
        assertEquals("max", user.getLoginName());
    }

    @Test
    public void findByLoginName() {
        User user = this.userService.findByLoginName("max");
        assertEquals(1, user.getId());
    }

    @Test
    public void registerUser() {
        User user = new User("Jim", "user1", "123");
        this.userService.registerUser(user);
        User registerUser = this.userService.findByLoginName("Jim");
        assertEquals("user1", registerUser.getName());
    }

    @Test
    public void updateUser() {
        User user = this.userService.findByLoginName("max");
        user.setName("Mad Max");
        this.userService.updateUser(user);
        User searchUser = this.userService.findByLoginName("max");
        assertEquals("Mad Max", searchUser.getName());
    }

    @Test
    public void deleteUser() {
        User user = this.userService.findByLoginName("Jim");
        this.userService.deleteUser(user.getId());
        User searchUser = this.userService.findByLoginName("Jim");
        assertEquals(null, searchUser);
    }
}
