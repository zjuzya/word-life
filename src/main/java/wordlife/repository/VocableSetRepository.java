package wordlife.repository;

import org.springframework.data.repository.Repository;
import wordlife.entity.VocableSet;

import java.util.List;

public interface VocableSetRepository extends Repository<VocableSet, Long> {
    VocableSet save(VocableSet vocableSet);
    VocableSet findById(Long id);
    VocableSet findByName(String name);
    List<VocableSet> findAll();
}
