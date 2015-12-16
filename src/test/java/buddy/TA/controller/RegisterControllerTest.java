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
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.assertEquals;
/**
 * Created by zhihu on 15/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TeachingAssistanceApplication.class)
public class RegisterControllerTest {

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
    public void checkLoginNameTest() throws Exception {
        String json ="{\"loginName\":\"max\"}";
        MvcResult result = mockMvc.perform(post("/register/check").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),"failed");


    }

    @Test
    public void createUserTest() throws Exception {
        String json ="{\"loginName\":\"Jim\",\"name\":\"user1\",\"password\":\"1234\"}";
        MvcResult result = mockMvc.perform(post("/register/create").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),"success");


    }

    @After
    public void deleteData(){
        User user = userService.findByLoginName("Jim");
        if (user != null){
            userService.deleteUser(user.getId());
        }
    }


}
