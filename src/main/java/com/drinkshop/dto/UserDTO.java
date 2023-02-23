package com.drinkshop.dto;

import com.drinkshop.model.EnumForEntity;
import lombok.Data;

import java.sql.Date;

@Data
public class UserDTO extends BaseDTO {
    private String username;

    private String email;

    private String fullName;

    private String phone;

    private Date dateOfBirth;

    private EnumForEntity.UserType userType;
}
