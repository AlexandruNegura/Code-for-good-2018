package jp.morgan.jp.services;

import jp.morgan.jp.entities.Log;
import jp.morgan.jp.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public Log saveLog(Log log) {
        return logRepository.save(log);
    }

    @Override
    public List<Log> retrieveLogs() {
        return logRepository.findAll();
    }
}
