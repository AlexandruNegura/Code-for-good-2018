package jp.morgan.jp.services;

import jp.morgan.jp.entities.Carrer;
import jp.morgan.jp.repositories.CarrerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrerServiceImpl implements CarrerService {

    @Autowired
    private CarrerRepo carrerRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Carrer addUser(Carrer carrer) {
        encodePassword(carrer);

        return carrerRepo.save(carrer);
    }

    @Override
    public Carrer findUserByUsername(String username) {
        return carrerRepo.findJpUserByUsername(username);
    }

    private void encodePassword(Carrer carrer) {
        carrer.setPassword(bCryptPasswordEncoder.encode(carrer.getPassword()));
    }

    public boolean rawPasswordMatchesDbPassword(String rawPassword, String dbPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, dbPassword);
    }

    @Override
    public List<Carrer> findAllById(List<Long> ids) {
        return carrerRepo.findAllById(ids);
    }
}
