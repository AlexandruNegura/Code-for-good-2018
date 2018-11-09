package jp.morgan.jp.services;

import jp.morgan.jp.entities.JpUser;
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
    public JpUser addUser(JpUser jpUser) {
        encodePassword(jpUser);

        return userRepo.save(jpUser);
    }

    @Override
    public JpUser findUserByUsername(String username) {
        return userRepo.findJpUserByUsername(username);
    }

    private void encodePassword(JpUser jpUser) {
        jpUser.setPassword(bCryptPasswordEncoder.encode(jpUser.getPassword()));
    }

    public boolean rawPasswordMatchesDbPassword(String rawPassword, String dbPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, dbPassword);
    }
}
