package com.drinkshop.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String addressLine;

    @Column
    private String ward;

    @Column
    private String district;

    @Column
    private String province;
}
