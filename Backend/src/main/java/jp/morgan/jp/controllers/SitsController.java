package jp.morgan.jp.controllers;

import jp.morgan.jp.entities.Carrer;
import jp.morgan.jp.entities.Sit;
import jp.morgan.jp.models.SitModel;
import jp.morgan.jp.services.CarrerService;
import jp.morgan.jp.services.SitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = SitsController.SITS_CONTROLLER_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SitsController {
    static final String SITS_CONTROLLER_PATH = "/api/sit";

    @Autowired
    private CarrerService carrerService;

    @Autowired
    private SitService sitService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add-sit")
    ResponseEntity<Sit> addSit(@RequestBody SitModel sitModel) throws Exception {
        List<Carrer> carrers = carrerService.findAllById(sitModel.carrersIds);

        if(carrers.size() != sitModel.carrersIds.size()) {
            throw new Exception("Invalid carrers ids");
        }

        Sit sit = modelMapper.map(sitModel, Sit.class);

        carrers.forEach(carrer -> carrer.addSit(sit));
        sit.setCarrerList(carrers);

        sitService.addSit(sit);

        return new ResponseEntity<>(sit, OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<List<Sit>> getAllSits() {
        return new ResponseEntity<>(sitService.getAllSits(), OK);
    }
}
