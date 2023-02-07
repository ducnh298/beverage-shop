package com.drinkshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String fullName;

    @Column
    private String phone;

    @Column
    private Date dateOfBirth;

    @Column
    private EnumForEntity.UserType userType;

}
