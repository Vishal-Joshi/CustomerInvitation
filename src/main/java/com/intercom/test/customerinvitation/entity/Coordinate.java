package com.intercom.test.customerinvitation.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Coordinate {
    private double latitude;
    private double longitude;
}
