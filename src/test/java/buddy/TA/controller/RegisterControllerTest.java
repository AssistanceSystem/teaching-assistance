package buddy.TA.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import buddy.TA.TeachingAssistanceApplication;
import buddy.TA.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by zhihu on 15/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TeachingAssistanceApplication.class)
public class RegisterControllerTest {

    private String json ="{\"loginName\":\"Jim\",\"name\":\"user1\",\"password\":\"1234\"}";

    @Autowired
    private UserService userService;

    private final MockMvc mockMvc = standaloneSetup(new RegisterController())
            .build();


    @Test
    public void validate_get_address() throws Exception {
        String re = MockUtil.mock(this.mockMvc, "/register/create", json);
        assertEquals("success", re);

    }
}
