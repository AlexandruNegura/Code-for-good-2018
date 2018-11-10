package jp.morgan.jp.models;

import jp.morgan.jp.Utils.UserType;

import java.util.ArrayList;
import java.util.List;

public class CarrerOutDTO {
    public Long id;

    public String name;

    public String username;
    
    public List<Long> sitsId = new ArrayList<>();

    public UserType userType;

    public Boolean isLateHome;
}
