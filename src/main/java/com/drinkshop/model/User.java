package com.drinkshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private EnumForEntity.UserType userType;

}
