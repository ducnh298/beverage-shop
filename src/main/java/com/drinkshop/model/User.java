package com.drinkshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "user", schema = "public")
@NoArgsConstructor
@Getter
@Setter
@ToString
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
