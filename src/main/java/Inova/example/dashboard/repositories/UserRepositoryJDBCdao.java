package Inova.example.dashboard.repositories;

import Inova.example.dashboard.models.dto.AddressDto;
import Inova.example.dashboard.models.dto.UserRequest;
import Inova.example.dashboard.models.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryJDBCdao {
    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<UserDto> getAllUsers(UserRequest searchRQ){
        final List<UserDto> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT                                                                               \n");
        SQL.append(" u.id,                                                                               \n");
        SQL.append(" u.name,                                                                             \n");
        SQL.append(" a.city  ,                                                                           \n");
        SQL.append("a.street                                                                             \n");
        SQL.append("FROM user_table u                                                                    \n");
        SQL.append("INNER JOIN address_table a                                                           \n");
        SQL.append("ON u.address_id=a.id                                                                 \n");
        if (StringUtils.isNotEmpty(searchRQ.getName())) {
            SQL.append("WHERE UPPER(u.name) LIKE '%" + searchRQ.getName().toUpperCase() + "%'            \n");

        }
        if (!searchRQ.getId().equals(null)) {
            params.put("id", searchRQ.getId());
            SQL.append("AND u.id=:id                                                                      \n");

        }

        return namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<List<UserDto>>() {

            @Nullable
            @Override
            public List<UserDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    UserDto userDto = new UserDto();
                    userDto.setUserId(rs.getInt("id"));
                    userDto.setName(rs.getString("name"));

                    AddressDto addressDto=new AddressDto();
                    addressDto.setCity(rs.getString("city"));
                    addressDto.setStreet(rs.getString("street"));

                    userDto.setAddress(addressDto);
                    result.add(userDto);
                }
                return result;
            }
        });
    }
}
