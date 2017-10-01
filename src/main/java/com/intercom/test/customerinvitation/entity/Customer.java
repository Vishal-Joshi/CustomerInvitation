package com.intercom.test.customerinvitation.entity;

import lombok.Value;

@Value
public class Customer {
    private double latitude;
    private String user_id;
    private String name;
    private double longitude;
}
