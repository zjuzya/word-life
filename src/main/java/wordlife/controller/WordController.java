package wordlife.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wordlife.entity.User;
import wordlife.entity.Vocable;
import wordlife.entity.VocableSet;
import wordlife.repository.UserRepository;
import wordlife.repository.VocableRepository;
import wordlife.repository.VocableSetRepository;
import wordlife.util.VocableUtil;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WordController {
    private UserRepository userRepository;
    private VocableRepository vocableRepository;
    private VocableSetRepository vocableSetRepository;
    private VocableUtil vocableUtil;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WordController(UserRepository userRepository, VocableSetRepository vocableSetRepository,
                   VocableRepository vocableRepository, VocableUtil vocableUtil) {
        this.userRepository = userRepository;
        this.vocableSetRepository = vocableSetRepository;
        this.vocableRepository = vocableRepository;
        this.vocableUtil = vocableUtil;
    }

    @RequestMapping(value = "/get-word/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Vocable> getWord(@PathVariable String username) {
        User user = userRepository.findByName(username);
        Long userId = user.getId();
        ArrayList<Vocable> res = new ArrayList<>();
        logger.info("find vocable set for " + username);
        for (Vocable v : vocableSetRepository.findById(user.getVocableSetId()).getVocables()) {
            res.add(vocableRepository.findByUserIdAndVocable(userId, v.getVocable()));
        }
        return res;
    }

    @RequestMapping(value = "/remember/{username}/{word}", method = RequestMethod.POST)
    @ResponseBody
    public String remember(@PathVariable String username, @PathVariable String word) {
        logger.info("remember " + username + " " + word);
        vocableUtil.modifyPoints(username, word, +10);
        return "success";
    }

    @RequestMapping(value = "/forget/{username}/{word}", method = RequestMethod.POST)
    @ResponseBody
    public String forget(@PathVariable String username, @PathVariable String word) {
        logger.info("forget " + username + " " + word);
        vocableUtil.modifyPoints(username, word, -10);
        return "success";
    }

    @RequestMapping(value = "/get-sets/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<VocableSet> getAllSets(@PathVariable String username) {
        logger.info("get sets");
        ArrayList<VocableSet> res = new ArrayList<>();
        User user = userRepository.findByName(username);
        Long userSetId = user.getVocableSetId();
        res.add(vocableSetRepository.findById(userSetId));
        for (VocableSet vocableSet : vocableSetRepository.findAll()) {
            if (vocableSet.getType() == 0 && !vocableSet.getId().equals(userSetId)) {
                res.add(vocableSet);
            }
        }
        return res;
    }

    @RequestMapping(value = "/select-set/{username}", method = RequestMethod.POST)
    @ResponseBody
    public String selectSet(@PathVariable String username, @RequestParam("userWordList") String list) {
        User user = userRepository.findByName(username);
        VocableSet vocableSet = vocableSetRepository.findByName(list);
        if (vocableSet == null) {
            return "failure";
        }
        user.setVocableSetId(vocableSet.getId());
        userRepository.save(user);
        logger.info("select " + username + " list" + list);
        return "success";
    }

    @RequestMapping(value = "/get-selected-set/{username}", method = RequestMethod.GET)
    @ResponseBody
    public String getSelectedSet(@PathVariable String username) {
        User user = userRepository.findByName(username);
        VocableSet vocableSet = vocableSetRepository.findById(user.getVocableSetId());
        return vocableSet.getName();
    }

    @RequestMapping(value = "/get-user-set/{username}", method = RequestMethod.GET)
    @ResponseBody
    public List<Vocable> getUserSet(@PathVariable String username) {
        User user = userRepository.findByName(username);
        return vocableRepository.findAllByUserId(user.getId());
    }

    @RequestMapping(value = "/user-word/{username}", method = RequestMethod.POST)
    @ResponseBody
    public String addUserWord(@PathVariable String username,
                              @RequestParam("word") String word,
                              @RequestParam("description") String description) {
        User user = userRepository.findByName(username);
        Vocable vocable = new Vocable(word, description, user.getId());
        vocableRepository.save(vocable);
        return "success";
    }


}
