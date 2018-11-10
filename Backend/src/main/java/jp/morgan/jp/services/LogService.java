package jp.morgan.jp.services;

import jp.morgan.jp.entities.Log;

import java.util.List;

public interface LogService {
    Log saveLog(Log log);

    List<Log> retrieveLogs();
}
