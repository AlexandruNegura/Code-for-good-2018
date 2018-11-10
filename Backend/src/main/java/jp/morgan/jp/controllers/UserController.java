package jp.morgan.jp.controllers;

import jp.morgan.jp.entities.Carrer;
import jp.morgan.jp.models.UserLoginModel;
import jp.morgan.jp.models.UserSignUpModel;
import jp.morgan.jp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = UserController.USER_CONTROLLER_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    static final String USER_CONTROLLER_PATH = "/api/user";

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    ResponseEntity<Carrer> login(@RequestBody UserLoginModel userLoginModel) throws Exception {

        Carrer carrer = userService.findUserByUsername(userLoginModel.username);

        if (carrer == null) {
            throw new Exception("Invalid username or password");
        }

        if (!userService.rawPasswordMatchesDbPassword(userLoginModel.password, carrer.getPassword())) {
            throw new Exception("Invalid username or password");
        }

        return new ResponseEntity<>(carrer, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    ResponseEntity<Carrer> addUser(@RequestBody UserSignUpModel user) {
        Carrer carrer = userService.addUser(modelMapper.map(user, Carrer.class));

        return new ResponseEntity<>(carrer, HttpStatus.OK);
    }

}
