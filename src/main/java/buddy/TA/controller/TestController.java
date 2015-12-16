package buddy.TA.controller;

import buddy.TA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhihu on 15/12/16.
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
