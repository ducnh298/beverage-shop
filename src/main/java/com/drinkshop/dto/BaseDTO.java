package com.drinkshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BaseDTO {
    private Integer id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Timestamp createdDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String createdBy;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Timestamp modifiedDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String modifiedBy;
}
