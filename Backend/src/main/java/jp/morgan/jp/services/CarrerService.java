package jp.morgan.jp.services;

import jp.morgan.jp.entities.Carrer;

import java.util.List;

public interface CarrerService {

    Carrer addUser(Carrer carrer);

    Carrer findUserByUsername(String username);

    boolean rawPasswordMatchesDbPassword(String rawPassword, String dbPassword);

    List<Carrer> findAllById(List<Long> ids);

    List<Carrer> getAll();

    Carrer findById(Long id);

    Carrer save(Carrer carrer);
}
