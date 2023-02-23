package com.drinkshop.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AddressDTO extends BaseDTO {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private UserDTO user;

    private String userFullName;

    private String addressLine;

    private String ward;

    private String district;

    private String province;

    public void setUser(UserDTO user) {
        this.user = user;
        this.userFullName = this.user.getFullName();
    }
}
