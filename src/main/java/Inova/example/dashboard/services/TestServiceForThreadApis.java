package Inova.example.dashboard.services;

import Inova.example.dashboard.models.UserConfigured;
import Inova.example.dashboard.models.WorkerTable;
import Inova.example.dashboard.models.dto.Workers;
import Inova.example.dashboard.repositories.WorkerRepository;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class TestServiceForThreadApis {
    @Autowired
    WorkerRepository testRepository;
    @Autowired
    Gson gson;

    @Value("${include_teams}")
    private  String include_teams;
    @Value("${recaptchaV3}")
    private  String  recaptchaV3;

    public WorkerTable saveWorkers() throws IOException, InterruptedException, JSONException {
        String token1= getAccessToken();
        String token2=token1.substring(7,token1.length());
        String token=token2.substring(1,token2.length()-2);

        var httpClient = HttpClient.newBuilder().build();
        var host = "https://api.clickup.com";
        var pathname = "/api/v2/user";
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(host + pathname ))
                .header("Authorization",token)
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Workers workers = gson.fromJson(response.body(), Workers.class);
        System.out.println(workers+"workers saved in the database table-----------------");
        WorkerTable user=new WorkerTable();

        user.setName(workers.getUser().getUsername());
        user.setEmail(workers.getUser().getEmail());
        WorkerTable workerNew=testRepository.saveAndFlush(user);

        return workerNew;
    }

    public String getAccessToken() throws IOException, InterruptedException, JSONException {
        UserConfigured user=new UserConfigured();

        var httpClient = HttpClient.newBuilder().build();

        HashMap<String, String> params = new HashMap<>();
        params.put("include_teams", include_teams);
        params.put("recaptchaV3", recaptchaV3);

        var query = params.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(params.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        var host = "https://api.clickup.com";
        var pathname = "/v1/login";
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(host + pathname + '?' + query))
                .header("Authorization", getBasicAuthenticationHeader(user.getUsername(), user.getPassword()))
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject= new JSONObject(response.body());
        String token= (String) jsonObject.get("token");
        RestTemplate restTemplate=new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity entity = new HttpEntity(headers);

        String result = restTemplate.postForObject("https://api.clickup.com/core/v1/devKey",entity,String.class);
        return result;
    }

    public String getBearerToken() throws IOException, InterruptedException, JSONException {

        UserConfigured user=new UserConfigured();
        var httpClient = HttpClient.newBuilder().build();

        HashMap<String, String> params = new HashMap<>();
        params.put("include_teams", include_teams);
        params.put("recaptchaV3", recaptchaV3);

        var query = params.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(params.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        var host = "https://api.clickup.com";
        var pathname = "/v1/login";
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(host + pathname + '?' + query))
                .header("Authorization", getBasicAuthenticationHeader(user.getUsername(), user.getPassword()))
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject= new JSONObject(response.body());
        String token= (String) jsonObject.get("token");
        return token;
    }
    private static final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

}
