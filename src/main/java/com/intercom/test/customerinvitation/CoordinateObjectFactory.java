package com.intercom.test.customerinvitation;

import com.intercom.test.customerinvitation.entities.Coordinate;
import org.springframework.stereotype.Service;

/**
 * This class should be used to create {@link Coordinate} objects for
 * given {@link double} latitude and {@link double} longitude
 * @author Vishal Joshi
 */
@Service
public class CoordinateObjectFactory {
    public Coordinate createObject(double latitude, double longitude) {
        return new Coordinate(latitude, longitude);
    }
}
