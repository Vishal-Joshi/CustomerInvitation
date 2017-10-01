package com.intercom.test.customerinvitation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Customer {
    private double latitude;
    @JsonProperty(value = "user_id")
    private String userId;
    private String name;
    private double longitude;
}
