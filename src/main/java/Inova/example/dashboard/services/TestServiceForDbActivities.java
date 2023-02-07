package Inova.example.dashboard.services;

import Inova.example.dashboard.models.dto.UserRequest;
import Inova.example.dashboard.models.dto.UserDto;
import Inova.example.dashboard.repositories.UserRepositoryJDBCdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceForDbActivities {
    @Autowired
    UserRepositoryJDBCdao testRepositoryJDBCdao;
    public List<UserDto> GetListOfUsers(UserRequest userRequest){
        return testRepositoryJDBCdao.getAllUsers(userRequest);
    }
}
