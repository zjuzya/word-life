package wordlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wordlife.entity.User;
import wordlife.repository.UserRepository;

@Controller
public class IndexController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        return "index";
    }

    private UserRepository userRepository;
    @Autowired IndexController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public @ResponseBody
    User hello() {
        return userRepository.findByName("zya");
    }

}
