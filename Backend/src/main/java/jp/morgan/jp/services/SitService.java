package jp.morgan.jp.services;

import jp.morgan.jp.entities.Sit;

import java.util.List;

public interface SitService {
    Sit addSit(Sit sit);

    List<Sit> getAllSits();

    Sit getSitById(Long id);
}
