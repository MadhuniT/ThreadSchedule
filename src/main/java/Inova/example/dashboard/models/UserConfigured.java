package Inova.example.dashboard.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConfigured {
    private  String username="api.clickup@inovaitsys.com";
    private  String password="Api.clickup@inova";
}


