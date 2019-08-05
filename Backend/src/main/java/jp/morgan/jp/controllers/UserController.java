package jp.morgan.jp.controllers;

import jp.morgan.jp.entities.JpUser;
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
    ResponseEntity<JpUser> login(@RequestBody UserLoginModel userLoginModel) throws Exception {

        JpUser jpUser = userService.findUserByUsername(userLoginModel.username);

        if (jpUser == null) {
            throw new Exception("Invalid username or password");
        }

        if (!userService.rawPasswordMatchesDbPassword(userLoginModel.password, jpUser.getPassword())) {
            throw new Exception("Invalid username or password");
        }

        return new ResponseEntity<>(jpUser, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    ResponseEntity<JpUser> addUser(@RequestBody UserSignUpModel user) {
        JpUser jpUser = userService.addUser(modelMapper.map(user, JpUser.class));

        return new ResponseEntity<>(jpUser, HttpStatus.OK);
    }

}
