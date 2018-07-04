package wordlife.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Vocable {

    private @Id
    @GeneratedValue
    Long id;
    private String vocable;
    private String description;

    private @Version
    @JsonIgnore
    Long version;

    Long userId;

    private int points = 0;

    private Vocable() {
        this.userId = new Long(-1);
    }

    public Vocable(Vocable vocable) {
        this.vocable = vocable.vocable;
        this.description = vocable.description;
        this.userId = vocable.userId;
        this.points = vocable.points;
    }

    public Vocable(String vocable, String description) {
        this();
        this.vocable = vocable;
        this.description = description;
    }


    public Vocable(String vocable, String description, Long userId) {
        this(vocable, description);
        this.userId = userId;
    }

    public Vocable(String vocable, String description, Long userId, int points) {
        this(vocable, description, userId);
        // userId's remember points on this vocable
        this.points = points;
    }
}