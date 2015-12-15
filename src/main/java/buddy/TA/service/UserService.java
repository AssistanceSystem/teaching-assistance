package buddy.TA.service;


import buddy.TA.repository.UserDao;
import buddy.TA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.Clock;

import java.util.List;

/**
 * Created by zhihu on 15/12/14.
 */
@Service
public class UserService {

    private UserDao userDao;

    private Clock clock = Clock.DEFAULT;

    public UserService(){}

    public List<User> getAllUser() {

        return (List<User>) userDao.findAll();
    }

    public User getUser(Long id) {

        return userDao.findOne(id);
    }

    public User findByLoginName(String loginName) {

        return userDao.findByLoginName(loginName);
    }

    public void registerUser(User user) {
        user.setRegisterDate(clock.getCurrentDate());
        userDao.save(user);
    }

    public void updateUser(User user) {
        userDao.save(user);
    }

    public void deleteUser(Long id) {
        userDao.delete(id);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
