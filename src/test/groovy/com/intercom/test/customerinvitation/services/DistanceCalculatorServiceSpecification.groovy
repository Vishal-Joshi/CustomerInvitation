package com.intercom.test.customerinvitation.services

import com.intercom.test.customerinvitation.entities.Coordinate
import spock.lang.Specification


class DistanceCalculatorServiceSpecification extends Specification {

    def "service should take two gps coordinates and find correct distance in kilometers"() {
        given:
        DistanceCalculatorService distanceCalculatorService = new DistanceCalculatorService()
        Coordinate london = Coordinate.builder().latitude(51.5085).longitude(-0.1257).build()
        Coordinate dublin = Coordinate.builder().latitude(53.3331).longitude(-6.2489).build()
        //source https://www.distancecalculator.net/from-london-to-dublin
        double expectedDistanceInKms = 462

        when:
        double calculateDistanceInKilometers = distanceCalculatorService.calculateDistanceInKilometers(london, dublin)


        then:
        calculateDistanceInKilometers == expectedDistanceInKms
    }

    def "service should verify that distance between same set coordinates is 0.0"() {
        given:
        DistanceCalculatorService distanceCalculatorService = new DistanceCalculatorService()
        Coordinate london = Coordinate.builder().latitude(51.5085).longitude(-0.1257).build()
        Coordinate againLondon = Coordinate.builder().latitude(51.5085).longitude(-0.1257).build()
        //source https://www.distancecalculator.net/from-london-to-dublin
        double expectedDistanceInKms = 0.0

        when:
        double calculateDistanceInKilometers = distanceCalculatorService.calculateDistanceInKilometers(london, againLondon)


        then:
        calculateDistanceInKilometers == expectedDistanceInKms
    }
}
