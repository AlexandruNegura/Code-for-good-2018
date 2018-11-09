package jp.morgan.jp.services;

import jp.morgan.jp.entities.JpUser;

public interface UserService {

    JpUser addUser(JpUser jpUser);

    JpUser findUserByUsername(String username);

    boolean rawPasswordMatchesDbPassword(String rawPassword, String dbPassword);

    }
