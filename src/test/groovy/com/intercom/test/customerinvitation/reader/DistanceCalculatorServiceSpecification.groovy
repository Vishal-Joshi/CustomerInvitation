package com.intercom.test.customerinvitation.reader

import com.intercom.test.customerinvitation.entity.Coordinate
import spock.lang.Specification


class DistanceCalculatorServiceSpecification extends Specification {

    def "service should take two gps coordinates and find correct distance in kilometers"() {
        given:
        DistanceCalculatorService distanceCalculatorService = new DistanceCalculatorService()
        Coordinate london = new Coordinate(51.5085, -0.1257)
        Coordinate dublin = new Coordinate(53.3331, -6.2489)
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
        Coordinate london = new Coordinate(51.5085, -0.1257)
        Coordinate againLondon = new Coordinate(51.5085, -0.1257)
        //source https://www.distancecalculator.net/from-london-to-dublin
        double expectedDistanceInKms = 0.0

        when:
        double calculateDistanceInKilometers = distanceCalculatorService.calculateDistanceInKilometers(london, againLondon)


        then:
        calculateDistanceInKilometers == expectedDistanceInKms
    }
}
