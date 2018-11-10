package jp.morgan.jp.controllers;

import jp.morgan.jp.entities.Log;
import jp.morgan.jp.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = LogController.LOG_CONTORLLER_PATH,
                consumes = MediaType.APPLICATION_JSON_VALUE)
public class LogController {
    static final String LOG_CONTORLLER_PATH = "/api/logs";

    @Autowired
    private LogService logService;

    @GetMapping("")
    public ResponseEntity<List<Log>> getAllLogs() {
        return new ResponseEntity<>(logService.retrieveLogs(), OK);
    }
}
