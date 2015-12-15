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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json")
    public String createUser ( @RequestBody JSONObject jsonObj, HttpServletResponse response){
        User user = new User(jsonObj.getString("loginName"),
                jsonObj.getString("name"),
                jsonObj.getString("password"));
        userService.registerUser(user);
        return userService.findByLoginName(jsonObj.getString("loginName")).toString();
    }


    private void renderData(HttpServletResponse response, String data) {
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.print(data);
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != printWriter) {
                printWriter.flush();
                printWriter.close();
            }
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
