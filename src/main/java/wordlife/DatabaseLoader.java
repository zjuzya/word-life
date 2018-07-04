package wordlife;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import wordlife.entity.User;
import wordlife.entity.Vocable;
import wordlife.entity.VocableSet;
import wordlife.repository.UserRepository;
import wordlife.repository.VocableRepository;
import wordlife.repository.VocableSetRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final VocableRepository vocables;
    private final UserRepository users;
    private final VocableSetRepository vocableSetRepository;

    @Autowired
    public DatabaseLoader(VocableRepository vocableRepository,
                          UserRepository userRepository,
                          VocableSetRepository vocableSetRepository) {

        this.vocables = vocableRepository;
        this.users = userRepository;
        this.vocableSetRepository = vocableSetRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        VocableSet set4 = new VocableSet("4");
        VocableSet set6 = new VocableSet("6");

        // load vocables to set.
        set4.addVocable(vocables.save(new Vocable("abandon", "leave something unused.")));
        set4.addVocable(vocables.save(new Vocable("baby", "very young child")));
        set4.addVocable(vocables.save(new Vocable("cabbage", "a large round green vegetable")));
        set6.addVocable(vocables.save(new Vocable("abnormal", "different from normal")));
        set6.addVocable(vocables.save(new Vocable("bachelor", "man not married")));
        set6.addVocable(vocables.save(new Vocable("cafeteria","restaurant")));

        vocableSetRepository.save(set4);
        vocableSetRepository.save(set6);

        // load user
        User zya = this.users.save(new User("zya", "zya", "3150102227@zju.edu.cn", set4.getId()));
        User zya2 = this.users.save(new User("zya2", "zya2", "596952403@qq.com", set6.getId()));

        // load vocables for users
        this.vocables.save(new Vocable("abandon", "leave something unused.", zya.getId(), 10));
        this.vocables.save(new Vocable("baby", "very young child", zya.getId(), 20));
        this.vocables.save(new Vocable("cabbage", "a large round green vegetable", zya.getId(), 50));
        this.vocables.save(new Vocable("abnormal", "different from normal", zya.getId(), 30));
        this.vocables.save(new Vocable("bachelor", "man not married", zya.getId(), 20));
        this.vocables.save(new Vocable("cafeteria","restaurant", zya.getId(), 0));

        this.vocables.save(new Vocable("abandon", "leave something unused.", zya2.getId()));
        this.vocables.save(new Vocable("baby", "very young child", zya2.getId()));
        this.vocables.save(new Vocable("cabbage", "a large round green vegetable", zya2.getId()));
        this.vocables.save(new Vocable("abnormal", "different from normal", zya2.getId()));
        this.vocables.save(new Vocable("bachelor", "man not married", zya2.getId()));
        this.vocables.save(new Vocable("cafeteria","restaurant", zya2.getId()));
    }
}
