package com.intercom.test.customerinvitation.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Coordinate {
    private double latitude;
    private double longitude;
}
