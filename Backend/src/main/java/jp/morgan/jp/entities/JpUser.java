package jp.morgan.jp.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE,
                setterVisibility = JsonAutoDetect.Visibility.ANY,
                getterVisibility = JsonAutoDetect.Visibility.ANY,
                isGetterVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class JpUser extends IdEntity {

    @Column
    private String name;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;


    public JpUser() {
    }

    public JpUser(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
