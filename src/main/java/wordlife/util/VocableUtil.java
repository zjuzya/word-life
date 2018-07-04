package wordlife.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wordlife.entity.User;
import wordlife.entity.Vocable;
import wordlife.repository.UserRepository;
import wordlife.repository.VocableRepository;

@Component
public class VocableUtil {
    private UserRepository userRepository;
    private VocableRepository vocableRepository;

    @Autowired
    VocableUtil(UserRepository userRepository, VocableRepository vocableRepository) {
        this.userRepository = userRepository;
        this.vocableRepository = vocableRepository;
    }

    public void modifyPoints(String username, String word, int delta) {
        User user = userRepository.findByName(username);
        Long userId = user.getId();
        Vocable vocable = vocableRepository.findByUserIdAndVocable(userId, word);
        if (vocable != null) {
            vocable.setPoints(vocable.getPoints() + delta);
            vocableRepository.save(vocable);
        }
    }
}
