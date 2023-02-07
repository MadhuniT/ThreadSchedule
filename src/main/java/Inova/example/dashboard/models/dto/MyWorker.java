package Inova.example.dashboard.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyWorker implements Serializable {
    private String username;
    private String email;

    @Override
    public String toString() {
        return "MyWorker{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
