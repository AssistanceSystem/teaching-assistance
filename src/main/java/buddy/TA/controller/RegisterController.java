package buddy.TA.controller;

import buddy.TA.entity.User;
import buddy.TA.repository.UserDao;
import buddy.TA.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.CheckRegister;

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
    @ResponseBody
    public ResponseEntity<String> createUser (HttpServletResponse response, @RequestBody JSONObject jsonObj){
        String loginName = jsonObj.getString("loginName");
        String name = jsonObj.getString("name");
        String password = jsonObj.getString("password");
        if (!CheckRegister.checkLoginName(loginName)){
            return new ResponseEntity<String>("loginName illegal",HttpStatus.BAD_REQUEST);
        }else if (!CheckRegister.checkPasswordLength(password)){
            return new ResponseEntity<String>("password illegal",HttpStatus.BAD_REQUEST);
        }else {
            User user = new User(loginName,name,password);
            userService.registerUser(user);
            return new ResponseEntity<String>("success",HttpStatus.OK);
        }
    }


    @RequestMapping(value = "check", method = RequestMethod.POST)
    @ResponseBody
    public String checkLoginName ( HttpServletResponse response,@RequestBody JSONObject jsonObj){
        String loginName = jsonObj.getString("loginName");
        User user = userService.findByLoginName(loginName);
        if (user == null){
            return "success";
        }else {
            return "failed";
        }
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
