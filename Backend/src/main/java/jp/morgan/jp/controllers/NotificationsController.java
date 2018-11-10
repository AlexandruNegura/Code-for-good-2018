package jp.morgan.jp.controllers;

import jp.morgan.jp.entities.Carrer;
import jp.morgan.jp.services.CarrerService;
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

    @GetMapping("/safe-to-child/{carrerId}/{sitId}")
    public void gotToChildSafe(@PathVariable("carrerId") Long carrerId,
                               @PathVariable("sitId") Long sitId) {
        Carrer carrer = carrerService.findById(carrerId);
        carrer.setGotToChildSafe(true);
        carrer.setGotHomeSafe(null);

        carrerService.save(carrer);
    }

    @GetMapping("/left-child-house/{carrerId}/{sitId}")
    public void leftChildHomeSafe(@PathVariable("carrerId") Long carrerId,
                                  @PathVariable("sitId") Long sitId) {
        Carrer carrer = carrerService.findById(carrerId);
        carrer.setLeftFromChildSafe(true);
        carrer.setGotHomeSafe(null);


        carrerService.save(carrer);
    }

    @GetMapping("/arrived-home-safe/{carrerId}/{sitId}")
    public void arrivedHomeSafe(@PathVariable("carrerId") Long carrerId,
                                @PathVariable("sitId") Long sitId) {
        Carrer carrer = carrerService.findById(carrerId);
        carrer.setGotHomeSafe(true);

        carrerService.save(carrer);
    }

    @GetMapping("/alert-not-home/{carrerId}/{sitId}")
    public ResponseEntity<Boolean> notHomeSafe(@PathVariable("carrerId") Long carrerId,
                                               @PathVariable("sitId") Long sitId) {
        Carrer carrer = carrerService.findById(carrerId);

        if (carrer.getGotHomeSafe() != null && carrer.getGotHomeSafe()) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
        }

        carrer.setGotHomeSafe(false);

        carrerService.save(carrer);

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
