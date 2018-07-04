package wordlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wordlife.entity.User;
import wordlife.util.UserUtil;

@Controller
public class LoginController {
    private UserUtil userUtil;

    @Autowired
    LoginController(UserUtil userUtil) {
        this.userUtil = userUtil;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userUtil.login(username, password);
        if (user == null) {
            return "redirect:/login.html?error=true";
        }
        return "redirect:/index.html?name="+username+"&user_id="+user.getId();
    }
}