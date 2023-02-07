package Inova.example.dashboard.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    private String name;
    private Integer id;
}
