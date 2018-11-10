package jp.morgan.jp.services;

import jp.morgan.jp.entities.Carrer;

public interface UserService {

    Carrer addUser(Carrer carrer);

    Carrer findUserByUsername(String username);

    boolean rawPasswordMatchesDbPassword(String rawPassword, String dbPassword);

    }
