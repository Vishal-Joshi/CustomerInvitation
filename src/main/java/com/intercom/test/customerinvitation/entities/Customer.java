package com.intercom.test.customerinvitation.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Customer {
    private double latitude;
    @JsonProperty(value = "user_id")
    private Long userId;
    private String name;
    private double longitude;
}
