package jp.morgan.jp.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jp.morgan.jp.Utils.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE,
                setterVisibility = JsonAutoDetect.Visibility.ANY,
                getterVisibility = JsonAutoDetect.Visibility.ANY,
                isGetterVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Carrer extends IdEntity {

    @Column
    private String name;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @JsonBackReference
    @ManyToMany
    private List<Sit> sits;

    @Column
    private UserType userType;

    @Column
    private Boolean isLateHome;


    public Carrer() {
    }

    public Carrer(String name, String username, String password) {
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

    public List<Sit> getSits() {
        return sits;
    }

    public void setSits(List<Sit> sits) {
        this.sits = sits;
    }

    public void addSit(Sit sit) {
        sits.add(sit);
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


}
