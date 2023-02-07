package Inova.example.dashboard.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDto implements Serializable {
    private Integer Id;

    private String city;

    private String street;

}
