package com.intercom.test.customerinvitation.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Customer {
    private double latitude;
    @JsonProperty(value = "user_id")
    private String userId;
    private String name;
    private double longitude;
}
