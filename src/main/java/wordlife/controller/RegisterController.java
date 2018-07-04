package wordlife.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wordlife.entity.User;
import wordlife.exception.DuplicateEmailException;
import wordlife.exception.DuplicateNameException;
import wordlife.util.UserUtil;

@Controller
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserUtil userUtil;

    @Autowired
    RegisterController(UserUtil userUtil) {
        this.userUtil = userUtil;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
        logger.info("Register userId(name=" + username + ",password=" + password + ",email=" + email + ")");
        User user;
        try {
            user = userUtil.register(new User(username, password, email));
        } catch (DuplicateNameException e) {
            return "redirect:/register.html?dupNameError=true";
        } catch (DuplicateEmailException e) {
            return "redirect:/register.html?dupEmailError=true";
        }
        if (null == user) {
            return "register";
        }
        return "redirect:/index.html?name=" + username + "&user_id=" + user.getId();
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewPage() {
        return "register";
    }

}
