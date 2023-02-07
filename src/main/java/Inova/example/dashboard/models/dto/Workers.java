package Inova.example.dashboard.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Workers implements Serializable {
    private MyWorker user;

    @Override
    public String toString() {
        return "Workers{" +
                "user=" + user +
                '}';
    }
}
