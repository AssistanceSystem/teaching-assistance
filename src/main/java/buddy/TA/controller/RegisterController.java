package buddy.TA.controller;

import buddy.TA.entity.User;
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

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhihu on 15/12/15.
 */

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    public RegisterController() {
    }

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    private static final String LOGIN_NAME_ILLEGAL = "loginName illegal";
    private static final String PASSWORD_ILLEGAL = "password illegal";
    private static final String SUCCESS = "success";
    private static final String FAILED = "failed";
    private static final String OK_CODE = "200";
    private static final String BAD_REQUEST_CODE = "400";

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createUser(HttpServletResponse response, @RequestBody JSONObject jsonObj) {
        ResponseMessage responseMessage;
        String loginName = jsonObj.getString("loginName");
        String name = jsonObj.getString("name");
        String password = jsonObj.getString("password");
        if (!CheckRegister.checkLoginName(loginName)) {
            responseMessage = new ResponseMessage(BAD_REQUEST_CODE, LOGIN_NAME_ILLEGAL);
            return new ResponseEntity<String>(responseMessage.toString(), HttpStatus.OK);
        } else if (!CheckRegister.checkPasswordLength(password)) {
            responseMessage = new ResponseMessage(BAD_REQUEST_CODE, PASSWORD_ILLEGAL);
            return new ResponseEntity<String>(responseMessage.toString(), HttpStatus.OK);
        } else {
            User user = new User(loginName, name, password);
            userService.registerUser(user);
            responseMessage = new ResponseMessage(OK_CODE, SUCCESS);
            return new ResponseEntity<String>(responseMessage.toString(), HttpStatus.OK);
        }
    }

    //TODO check used loginName
    @RequestMapping(value = "check", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> checkLoginName(HttpServletResponse response, @RequestBody JSONObject jsonObj) {
        String loginName = jsonObj.getString("loginName");
        User user = userService.findByLoginName(loginName);
        ResponseMessage responseMessage;
        if (user == null) {
            responseMessage = new ResponseMessage(OK_CODE, SUCCESS);
            return new ResponseEntity<String>(responseMessage.toString(), HttpStatus.OK);
        } else {
            responseMessage = new ResponseMessage(BAD_REQUEST_CODE, FAILED);
            return new ResponseEntity<String>(responseMessage.toString(), HttpStatus.OK);
        }
    }
}
