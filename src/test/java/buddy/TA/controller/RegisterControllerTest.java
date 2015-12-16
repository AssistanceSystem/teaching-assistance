package buddy.TA.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import buddy.TA.TeachingAssistanceApplication;
import buddy.TA.entity.User;
import buddy.TA.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * Created by zhihu on 15/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TeachingAssistanceApplication.class)
public class RegisterControllerTest {

    private String json ="{\"loginName\":\"Jim\",\"name\":\"user1\",\"password\":\"1234\"}";

//    @Autowired
//    private WebApplicationContext wac;

//    private MockMvc mockMvc;

//    @Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }


    @Autowired
    private RegisterController registerController;

    @Autowired
    private UserService userService;

    private  MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = standaloneSetup(registerController).build();
    }


    @Test
    public void validate_get_address() throws Exception {
//        MockUtil.mock(this.mockMvc, "/register/create", json);
//        assertEquals("success", re);
        mockMvc.perform(post("/register/create").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andExpect(
                        content()
                                .json("{\"response\" : \"success\" }"));

    }

    @After
    public void deleteData(){
        User user = userService.findByLoginName("Jim");
        userService.deleteUser(user.getId());
    }


}
