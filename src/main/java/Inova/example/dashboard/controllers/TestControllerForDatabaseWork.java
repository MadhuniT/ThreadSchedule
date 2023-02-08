package Inova.example.dashboard.controllers;

import Inova.example.dashboard.models.dto.UserRequest;
import Inova.example.dashboard.models.dto.UserDto;
import Inova.example.dashboard.services.TestServiceForDbActivities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TestControllerForDatabaseWork {
    @Autowired
    TestServiceForDbActivities testService;

    @PostMapping("/new")
    public ResponseEntity<List<UserDto>> test(@RequestBody UserRequest userRequest) {
        List<UserDto> userDto = this.testService.GetListOfUsers(userRequest);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
