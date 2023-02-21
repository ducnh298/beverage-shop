package com.drinkshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public abstract class BaseDTO {
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
