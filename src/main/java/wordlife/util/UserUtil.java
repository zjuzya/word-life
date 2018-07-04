package wordlife.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wordlife.entity.User;
import wordlife.entity.Vocable;
import wordlife.exception.DuplicateEmailException;
import wordlife.exception.DuplicateNameException;
import wordlife.repository.UserRepository;
import wordlife.repository.VocableRepository;

@Component
public class UserUtil {

    private UserRepository userRepository;
    private VocableRepository vocableRepository;

    @Autowired
    UserUtil(UserRepository userRepository, VocableRepository vocableRepository) {
        this.userRepository = userRepository;
        this.vocableRepository = vocableRepository;
    }

    public User register(User user) throws DuplicateNameException, DuplicateEmailException {
        if (userRepository.findByName(user.getName()) != null) {
            throw new DuplicateNameException();
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new DuplicateEmailException();
        }
        userRepository.save(user);
        addVocableForUser(user);
        return user;
    }

    public User login(String username, String password) {
        User user = userRepository.findByName(username);
        if (user != null && user.testPassword(password)) {
            user.setState(User.UserState.LoggedIn);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    private void addVocableForUser(User user) {
        Long userId = user.getId();
        for (Vocable vocable: vocableRepository.findAllByUserId(new Long(-1))) {
            Vocable newVocable = new Vocable(vocable);
            newVocable.setUserId(userId);
            newVocable.setPoints(0);
            vocableRepository.save(newVocable);
        }
    }
}