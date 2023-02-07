package Inova.example.dashboard.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Integer userId;

    private String name;

    private AddressDto address;

}
