package Inova.example.dashboard.controllers;

import Inova.example.dashboard.models.UserConfigured;
import Inova.example.dashboard.models.WorkerTable;
import Inova.example.dashboard.services.TestServiceForThreadApis;
import org.json.JSONException;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;


@RestController
public class TestControllerForThreadAPIS {
    @Autowired
    TestServiceForThreadApis testServiceForThreadApis;
    public String authenticate(UserConfigured user) throws IOException, InterruptedException, JSONException {
        return testServiceForThreadApis.authentication(user);
    }
    @Scheduled(fixedDelay = 2000)
    @PostMapping("/Thread-api1")
    public WorkerTable test1() throws IOException, InterruptedException, JSONException {
        Log.info("thread 1 started");
        UserConfigured user=new UserConfigured();
        String token=authenticate(user);
        String token1=authenticate(user).substring(7,token.length());

        WorkerTable result= testServiceForThreadApis.getWorkers(token1.substring(1,token1.length()-2));
        return result;
    }

    @Scheduled(fixedDelay = 1000)
    public String getd(){
        System.out.println("thread 2");
        return "thread 2";
    }
}



