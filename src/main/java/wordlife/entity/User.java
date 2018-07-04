package wordlife.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@ToString(exclude = "password")
@Entity
public class User {
    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private @Id
    @GeneratedValue
    Long id;

    private String name;

    private @JsonIgnore
    String password;

    private String email;

    private String code;

    private Long vocableSetId;

    public enum UserState {
        LoggedOut,
        LoggedIn,
    }

    private UserState state;

    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }

    public boolean testPassword(String password) {
        final Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("test pass = " + password + "; encoded pass=" + passwordEncoder.encode(password));
        return passwordEncoder.matches(password, this.password);
    }

    protected User() {
        this.state = UserState.LoggedOut;
        this.vocableSetId = new Long(1);
    }

    public User(String name, String password, String email) {
        this();
        this.name = name;
        this.email = email;
        this.setPassword(password);
        final Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Register userId(name=" + name + ",password=" + password + ",email=" + email + ")");
        logger.info("encode pass = " + this.password);
    }

    public User(String name, String password, String email, Long vocableSetId) {
        this(name, password, email);
        this.vocableSetId = vocableSetId;
    }
}
