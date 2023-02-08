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

    @Scheduled(fixedDelay = 1000)
    public String getd(){
        Log.info("Thread 1 started---------------------");
        Log.info("Thread 1 Finished---------------------");
        return "thread 2";

    }
    @Scheduled(fixedDelay = 1000)
    public String getd1(){
        Log.info("Thread 2 started------------------------");
        Log.info("Thread 2 finished------------------------");
        return "thread 2";
    }

    @Scheduled(fixedDelay = 2000)
    public WorkerTable saveWorkersData() throws IOException, InterruptedException, JSONException {
        Log.info("Thread 3 started---------------------");
        UserConfigured user=new UserConfigured();
        String token=authenticate(user);
        String token1=authenticate(user).substring(7,token.length());

        WorkerTable result= testServiceForThreadApis.saveWorkers(token1.substring(1,token1.length()-2));
        Log.info("Thread 3 finished----------------------");
        return result;
    }
    @Scheduled(fixedDelay = 1000)
    public String getd2(){
        Log.info("Thread 4 started------------------------");
        Log.info("Thread 4 finished------------------------");
        return "thread 2";
    }


}



