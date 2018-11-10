package jp.morgan.jp.services;

import jp.morgan.jp.entities.Carrer;
import jp.morgan.jp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Carrer addUser(Carrer carrer) {
        encodePassword(carrer);

        return userRepo.save(carrer);
    }

    @Override
    public Carrer findUserByUsername(String username) {
        return userRepo.findJpUserByUsername(username);
    }

    private void encodePassword(Carrer carrer) {
        carrer.setPassword(bCryptPasswordEncoder.encode(carrer.getPassword()));
    }

    public boolean rawPasswordMatchesDbPassword(String rawPassword, String dbPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, dbPassword);
    }
}
