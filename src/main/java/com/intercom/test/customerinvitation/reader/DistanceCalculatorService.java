package com.intercom.test.customerinvitation.reader;

import com.intercom.test.customerinvitation.entity.Coordinate;

/**
 * This service should be used to find distance between
 * two gps coordinates.
 *
 * @author Vishal Joshi
 */
public class DistanceCalculatorService {
    /**
     * {@see https://en.wikipedia.org/wiki/Earth_radius}
     */
    private static final double EARTH_RADIUS_IN_KMS = 6371.0;

    /**
     * This methods take two {@link Coordinate} and find distance between
     * them using the first formulae mentioned <a href="https://en.wikipedia.org/wiki/Great-circle_distance">here.</a>
     *
     * @param from source coordinate from where to start calculating distance
     * @param to   destination coordinate
     * @return distance in kilometers (kms) rounded to nearby digits using {@link Math#round(double)}
     */
    public double calculateDistanceInKilometers(Coordinate from, Coordinate to) {
        double fromLatitudeInRadians = degreesToRadians(from.getLatitude());
        double toLatitudeInRadians = degreesToRadians(to.getLatitude());

        double differenceBetweenLongitudesInRadians = degreesToRadians(to.getLongitude() - from.getLongitude());

        double centralAngle = Math.acos(Math.sin(fromLatitudeInRadians) * Math.sin(toLatitudeInRadians) +
                Math.cos(fromLatitudeInRadians) * Math.cos(toLatitudeInRadians) * Math.cos(differenceBetweenLongitudesInRadians));

        return Math.round(EARTH_RADIUS_IN_KMS * centralAngle);
    }

    private double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }
}
