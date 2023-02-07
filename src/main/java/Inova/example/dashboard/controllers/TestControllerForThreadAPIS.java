package Inova.example.dashboard.controllers;

import Inova.example.dashboard.models.UserConfigured;
import Inova.example.dashboard.models.WorkerTable;
import Inova.example.dashboard.services.TestServiceForThreadApis;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
public class TestControllerForThreadAPIS {
    @Autowired
    TestServiceForThreadApis testServiceForThreadApis;

    @PostMapping("/authentication")
    public String authenticate(@RequestBody UserConfigured user) throws IOException, InterruptedException, JSONException {
        return testServiceForThreadApis.authentication(user);
    }

    @GetMapping("/Thread-api1")
    public WorkerTable test1() throws IOException, InterruptedException{
        WorkerTable result= testServiceForThreadApis.getWorkers();
        return result;
    }

}



