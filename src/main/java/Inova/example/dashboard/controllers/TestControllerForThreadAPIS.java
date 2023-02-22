package Inova.example.dashboard.controllers;

import Inova.example.dashboard.models.WorkerTable;
import Inova.example.dashboard.services.TestServiceForThreadApis;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;


import java.io.IOException;

@Controller
public class TestControllerForThreadAPIS {
    @Autowired
    TestServiceForThreadApis testServiceForThreadApis;

    @Scheduled(fixedDelay = 2000)
    public String getProjects(){
        return "token1";

    }
    @Scheduled(fixedDelay = 2000)
    public String getProjectFolders(){
        return "token1";
    }

    @Scheduled(fixedDelay = 2000)
    public WorkerTable saveWorkersData() throws IOException, InterruptedException, JSONException {
        WorkerTable result= testServiceForThreadApis.saveWorkers();
        return result;
    }
    @Scheduled(fixedDelay = 2000)
    public String getProjectLists(){
        return "token1";
    }
}



