package wordlife.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
public class VocableSet {
    private @Id
    @GeneratedValue
    Long id;

    @ElementCollection
    List<Vocable> vocables = new ArrayList<>();
    String name;

    int type;
    // 0-standard
    // 1-user list

    private VocableSet() {
        this.type = 0;
    }

    public VocableSet(String name) {
        this();
        this.name = name;
    }

    public VocableSet(String name, int type){
        this(name);
        this.type = type;
    }

    public boolean exist(String vocable){
        for (Vocable v : vocables){
            if (v.getVocable().equals(vocable)){
                return true;
            }
        }
        return false;
    }

    public void addVocable(Vocable vocable) {
        if (exist(vocable.getVocable())){
            return;
        }
        vocables.add(vocable);
    }
}
