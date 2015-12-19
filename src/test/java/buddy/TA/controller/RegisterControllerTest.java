package buddy.TA.controller;

import buddy.TA.TeachingAssistanceApplication;
import buddy.TA.entity.User;
import buddy.TA.service.UserService;
import net.sf.json.JSONObject;
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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TeachingAssistanceApplication.class)
public class RegisterControllerTest {

    @Autowired
    private RegisterController registerController;

    @Autowired
    private UserService userService;

    private  MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(registerController).build();
    }

    @Test
    public void checkUsedLoginNameTest() throws Exception {
        String json = "{\"loginName\":\"max\"}";
        MvcResult result = mockMvc.perform(post("/register/check").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        JSONObject data = JSONObject.fromObject(response);
        assertEquals("failed", data.get("message"));
    }

    @Test
    public void checkUnusedLoginNameTest() throws Exception {
        String json = "{\"loginName\":\"tom\"}";
        MvcResult result = mockMvc.perform(post("/register/check").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        JSONObject data = JSONObject.fromObject(response);
        assertEquals("success", data.get("message"));
    }

    @Test
    public void successCreateUserTest() throws Exception {
        String json = "{\"loginName\":\"Jim@163.com\",\"name\":\"user1\",\"password\":\"123456\"}";
        MvcResult result = mockMvc.perform(post("/register/create").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        JSONObject data = JSONObject.fromObject(response);
        assertEquals("success", data.get("message"));
    }

    @Test
    public void loginNameErrorCreateUserTest() throws Exception {
        String json = "{\"loginName\":\"Jim\",\"name\":\"user1\",\"password\":\"123456\"}";
        MvcResult result = mockMvc.perform(post("/register/create").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        JSONObject data = JSONObject.fromObject(response);
        assertEquals("loginName illegal", data.get("message"));
    }

    @Test
    public void passwordShortErrorCreateUserTest() throws Exception {
        String json = "{\"loginName\":\"username@yahoo.corporate.in\",\"name\":\"user1\",\"password\":\"1234\"}";
        MvcResult result = mockMvc.perform(post("/register/create").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        JSONObject data = JSONObject.fromObject(response);
        assertEquals("password illegal", data.get("message"));
    }

    @Test
    public void passwordLongErrorCreateUserTest() throws Exception {
        String json = "{\"loginName\":\"Jim@163.com\",\"name\":\"user1\",\"password\":\"12345678901234567890\"}";
        MvcResult result = mockMvc.perform(post("/register/create").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        JSONObject data = JSONObject.fromObject(response);
        assertEquals("password illegal", data.get("message"));
    }

    @After
    public void deleteData() {
        User user = userService.findByLoginName("Jim@163.com");
        if (user != null) {
            userService.deleteUser(user.getId());
        }
    }
}
