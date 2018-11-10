package jp.morgan.jp.services;

import jp.morgan.jp.entities.Sit;
import jp.morgan.jp.repositories.SitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SitServiceImpl implements SitService {

    @Autowired
    private SitRepository sitRepository;

    @Override
    public Sit addSit(Sit sit) {
        return sitRepository.save(sit);
    }

    @Override
    public List<Sit> getAllSits() {
        return sitRepository.findAll();
    }

    @Override
    public Sit getSitById(Long id) {

        return sitRepository.getOne(id);
    }
}
