package jp.morgan.jp.controllers;

import jp.morgan.jp.Constants;
import jp.morgan.jp.entities.Carrer;
import jp.morgan.jp.entities.Log;
import jp.morgan.jp.entities.Sit;
import jp.morgan.jp.services.CarrerService;
import jp.morgan.jp.services.LogService;
import jp.morgan.jp.services.SitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = NotificationsController.NOTIFICATION_CONTROLLER_PATH,
                consumes = MediaType.APPLICATION_JSON_VALUE)
public class NotificationsController {
    static final String NOTIFICATION_CONTROLLER_PATH = "/api/notification";

    @Autowired
    private CarrerService carrerService;

    @Autowired
    private SitService sitService;

    @Autowired
    private LogService logService;

    @GetMapping("/safe-to-child/{carrerId}/{sitId}")
    public void gotToChildSafe(@PathVariable("carrerId") Long carrerId,
                               @PathVariable("sitId") Long sitId) {
        Carrer carrer = carrerService.findById(carrerId);
        Sit sit = sitService.getSitById(sitId);

        carrer.setGotToChildSafe(true);
        carrer.setGotHomeSafe(null);

        Log log = new Log(carrer, sit, Constants.CarerAction.GOT_CHILD_HOME_SAFE);

        logService.saveLog(log);

        carrerService.save(carrer);

    }

    @GetMapping("/left-child-house/{carrerId}/{sitId}")
    public void leftChildHomeSafe(@PathVariable("carrerId") Long carrerId,
                                  @PathVariable("sitId") Long sitId) {
        Carrer carrer = carrerService.findById(carrerId);
        Sit sit = sitService.getSitById(sitId);

        carrer.setLeftFromChildSafe(true);
        carrer.setGotHomeSafe(null);

        Log log = new Log(carrer, sit, Constants.CarerAction.LEFT_CHILD_HOME_SAFE);

        logService.saveLog(log);

        carrerService.save(carrer);
    }

    @GetMapping("/arrived-home-safe/{carrerId}/{sitId}")
    public void arrivedHomeSafe(@PathVariable("carrerId") Long carrerId,
                                @PathVariable("sitId") Long sitId) {
        Carrer carrer = carrerService.findById(carrerId);
        Sit sit = sitService.getSitById(sitId);

        carrer.setGotHomeSafe(true);

        Log log = new Log(carrer, sit, Constants.CarerAction.GOT_CARER_HOME_SAFE);

        logService.saveLog(log);

        carrerService.save(carrer);
    }

    @GetMapping("/alert-not-home/{carrerId}/{sitId}")
    public ResponseEntity<Boolean> notHomeSafe(@PathVariable("carrerId") Long carrerId,
                                               @PathVariable("sitId") Long sitId) {
        Carrer carrer = carrerService.findById(carrerId);
        Sit sit = sitService.getSitById(sitId);

        if (carrer.getGotHomeSafe() != null && carrer.getGotHomeSafe()) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
        }

        carrer.setGotHomeSafe(false);

        carrerService.save(carrer);

        Log log = new Log(carrer, sit, Constants.CarerAction.NOT_HOME_SAFE_OR_LATE);

        logService.saveLog(log);


        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
