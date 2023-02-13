package com.drinkshop.dto;

import com.drinkshop.model.EnumForEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    private String username;

    private String email;

    private String fullName;

    private String phone;

    private Date dateOfBirth;

    private EnumForEntity.UserType userType;
}
