package wordlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wordlife.repository.UserRepository;

@Controller
public class UserActivateController {
    private UserRepository userRepository;

    @Autowired
    public UserActivateController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @RequestMapping(value = "activate", method = RequestMethod.GET)
    public String active(@RequestParam(value="code") String code){
        if (userRepository.findByCode(code) != null){
            // TODO: authorize
        }
        return "index";
    }
}
