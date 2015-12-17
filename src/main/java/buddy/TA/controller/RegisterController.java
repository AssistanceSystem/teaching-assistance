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
import util.ResponseMessage;

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

    private static String LoginNameIllegal = "loginName illegal";
    private static String PasswordIllegal = "password illegal";
    private static String Success = "success";
    private static String Failed = "failed";
    private static String OK = "200";
    private static String BadRequest = "400";

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createUser (HttpServletResponse response, @RequestBody JSONObject jsonObj){
        ResponseMessage responseMessage;
        String loginName = jsonObj.getString("loginName");
        String name = jsonObj.getString("name");
        String password = jsonObj.getString("password");
        if (!CheckRegister.checkLoginName(loginName)){
            responseMessage = new ResponseMessage(BadRequest,LoginNameIllegal);
            return new ResponseEntity<String>(responseMessage.toString(),HttpStatus.BAD_REQUEST);
        }else if (!CheckRegister.checkPasswordLength(password)){
            responseMessage = new ResponseMessage(BadRequest,PasswordIllegal);
            return new ResponseEntity<String>(responseMessage.toString(),HttpStatus.BAD_REQUEST);
        }else {
            User user = new User(loginName,name,password);
            userService.registerUser(user);
            responseMessage = new ResponseMessage(OK,Success);
            return new ResponseEntity<String>(responseMessage.toString(),HttpStatus.OK);
        }
    }


    @RequestMapping(value = "check", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> checkLoginName ( HttpServletResponse response,@RequestBody JSONObject jsonObj){
        String loginName = jsonObj.getString("loginName");
        User user = userService.findByLoginName(loginName);
        ResponseMessage responseMessage;
        if (user == null){
            responseMessage = new ResponseMessage(OK,Success);
            return new ResponseEntity<String>(responseMessage.toString(),HttpStatus.OK);
        }else {
            responseMessage = new ResponseMessage(BadRequest,Failed);
            return new ResponseEntity<String>(responseMessage.toString(),HttpStatus.BAD_REQUEST);
        }
    }


//            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);


}
