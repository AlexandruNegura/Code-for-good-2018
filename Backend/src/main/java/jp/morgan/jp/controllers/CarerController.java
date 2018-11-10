package jp.morgan.jp.controllers;

import jp.morgan.jp.entities.Carrer;
import jp.morgan.jp.entities.Sit;
import jp.morgan.jp.models.CarrerOutDTO;
import jp.morgan.jp.models.UserLoginModel;
import jp.morgan.jp.models.UserSignUpModel;
import jp.morgan.jp.services.CarrerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = CarerController.USER_CONTROLLER_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CarerController {
    static final String USER_CONTROLLER_PATH = "/api/user";

    @Autowired
    private CarrerService carrerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    ResponseEntity<Carrer> login(@RequestBody UserLoginModel userLoginModel) throws Exception {

        Carrer carrer = carrerService.findUserByUsername(userLoginModel.username);

        if (carrer == null) {
            throw new Exception("Invalid username or password");
        }

        if (!carrerService.rawPasswordMatchesDbPassword(userLoginModel.password, carrer.getPassword())) {
            throw new Exception("Invalid username or password");
        }

        return new ResponseEntity<>(carrer, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    ResponseEntity<Carrer> addUser(@RequestBody UserSignUpModel user) {
        Carrer carrer = carrerService.addUser(modelMapper.map(user, Carrer.class));

        return new ResponseEntity<>(carrer, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<List<CarrerOutDTO>> getAllCarers() {
        List<Carrer> carrers = carrerService.getAll();

        List<CarrerOutDTO> carrerOutDTOS = carrers.stream()
                                                  .map(carrer -> modelMapper.map(carrer, CarrerOutDTO.class))
                                                  .collect(
                                                          Collectors.toList());

        for (Carrer carrer : carrers) {
            CarrerOutDTO carrerOutDTO = carrerOutDTOS.stream()
                                                     .filter(carrerOutDTO1 -> carrerOutDTO1.id.equals(carrer.getId()))
                                                     .findFirst()
                                                     .get();
            for (Sit sit : carrer.getSits()) {
                carrerOutDTO.sitsId.add(sit.getId());
            }
        }

        return new ResponseEntity<>(carrerOutDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}/getAll")
    ResponseEntity<List<Sit>> getAllSitsforCarer(@PathVariable("id") Long id) {
        Carrer carrer = carrerService.findById(id);

        return new ResponseEntity<>(carrer.getSits(), HttpStatus.OK);
    }

    @GetMapping("/get-all-not-home-safe")
    public ResponseEntity<List<Carrer>> getCarrerNotHomeSafe() {
        List<Carrer> carrers = carrerService.getAll();

        List<Carrer> notHomeSafeCarrers = carrers.stream().filter(carrer -> carrer.getGotHomeSafe() != null && !carrer.getGotHomeSafe()).collect(Collectors.toCollection(
                ArrayList::new));

        List<CarrerOutDTO> carrerOutDTOS = notHomeSafeCarrers.stream()
                                                  .map(carrer -> modelMapper.map(carrer, CarrerOutDTO.class))
                                                  .collect(
                                                          Collectors.toList());

        for (Carrer carrer : notHomeSafeCarrers) {
            CarrerOutDTO carrerOutDTO = carrerOutDTOS.stream()
                                                     .filter(carrerOutDTO1 -> carrerOutDTO1.id.equals(carrer.getId()))
                                                     .findFirst()
                                                     .get();
            for (Sit sit : carrer.getSits()) {
                carrerOutDTO.sitsId.add(sit.getId());
            }
        }

        return new ResponseEntity<>(notHomeSafeCarrers, HttpStatus.OK);
    }
}
