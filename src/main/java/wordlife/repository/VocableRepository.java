package wordlife.repository;

import org.springframework.data.repository.Repository;
import wordlife.entity.Vocable;

import java.util.List;

public interface VocableRepository extends Repository<Vocable, Long> {

	Vocable save(Vocable vocable);
	Vocable findByUserIdAndVocable(Long userId, String vocable);
	List<Vocable> findAllByUserId(Long userId);
}
