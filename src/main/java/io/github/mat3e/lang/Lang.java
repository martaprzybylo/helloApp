package io.github.mat3e.lang;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table (name = "LANGUAGES")
public class Lang {

    @Id
    @GeneratedValue (generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;

    @Column
    private String welcomeMsg;

    @Column
    private String code;

    /**
     * Hibernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    Lang() {
    }

    public Lang(Integer id, String welcomeMsg, String code) {
        this.id = id;
        this.welcomeMsg = welcomeMsg;
        this.code = code;
    }


    public Integer getId() {
        return id;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
