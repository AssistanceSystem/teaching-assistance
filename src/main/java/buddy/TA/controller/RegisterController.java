package buddy.TA.controller;

import buddy.TA.entity.User;
import buddy.TA.repository.UserDao;
import buddy.TA.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zhihu on 15/12/15.
 */

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    public RegisterController(){
    }

    public RegisterController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public void createUser ( HttpServletResponse response,@RequestBody JSONObject jsonObj){
        User user = new User(jsonObj.getString("loginName"),
                jsonObj.getString("name"),
                jsonObj.getString("password"));
        userService.registerUser(user);
        try{
            response.getWriter().write("{\"response\" : \"success\" }");
        }catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);

        }
//        return userService.findByLoginName(jsonObj.getString("loginName")).toString();
    }


    @RequestMapping(value = "check", method = RequestMethod.POST)
    public void checkLoginName ( HttpServletResponse response,@RequestBody JSONObject jsonObj){
        String loginName = jsonObj.getString("loginName");
        User user = userService.findByLoginName(loginName);
        try{
            if (user == null){
                response.getWriter().write("{\"response\" : \"success\" }");
             }else {
                response.getWriter().write("{\"response\" : \"error\" }");
            }
        }catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);

        }
//        return userService.findByLoginName(jsonObj.getString("loginName")).toString();
    }

    private HttpServletRequest encodingData(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);

        }
        return request;
    }

}
