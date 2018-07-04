package wordlife.repository;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import wordlife.entity.User;

@RepositoryRestResource(exported = false)
public interface UserRepository extends Repository<User, Long> {

    User save(User user);

    User findByName(String name);

    User findByEmail(String email);

    User findByCode(String code);

}
