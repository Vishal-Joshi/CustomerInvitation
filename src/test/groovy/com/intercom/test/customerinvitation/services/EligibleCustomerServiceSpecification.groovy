package com.intercom.test.customerinvitation.services

import com.intercom.test.customerinvitation.factories.CoordinateObjectFactory
import com.intercom.test.customerinvitation.entities.Coordinate
import com.intercom.test.customerinvitation.entities.Customer
import spock.lang.Specification


class EligibleCustomerServiceSpecification extends Specification {

    CustomerFileReaderService mockCustomerFileReaderService = Mock(CustomerFileReaderService)
    DistanceCalculatorService mockDistanceCalculatorService = Mock(DistanceCalculatorService)
    CoordinateObjectFactory mockCoordinateObjectFactory = Mock(CoordinateObjectFactory)

    def "service should return eligible customers lying within passed radius and sorted by userId"() {
        given:
        EligibleCustomerService eligibleCustomerService = new EligibleCustomerService(mockDistanceCalculatorService, mockCustomerFileReaderService, mockCoordinateObjectFactory)
        String path = "path"
        double radiusInKms = 100

        //London 51.5074° N, 0.1278° W
        def customer1 = new Customer(53.339428, "1", "Vishal", -6.257664)
        def customer2 = new Customer(53.349428, "2", "Vishal", -6.267664)
        def customerFarOffInLondon = new Customer(51.5074, "3", "Vishal", 0.1278)
        List<Customer> expectedListOfCustomers = [customer1, customer2, customerFarOffInLondon]

        //Dublin 53.339428, -6.257664.
        Coordinate dublin = new Coordinate(53.339428, -6.257664)
        Coordinate nearByDublin = new Coordinate(53.349428, -6.267664)
        Coordinate anyFarOffPlaceFromDublin = new Coordinate(51.5074, 0.1278)

        when:
        List<Customer> customers = eligibleCustomerService.computeEligibleCustomers(path, dublin, radiusInKms)

        then:
        1 * mockCustomerFileReaderService.readFile(_ as String) >> expectedListOfCustomers
        1 * mockCoordinateObjectFactory.createObject(53.339428, -6.257664) >> dublin
        1 * mockCoordinateObjectFactory.createObject(53.349428, -6.267664) >> nearByDublin
        1 * mockCoordinateObjectFactory.createObject(51.5074, 0.1278) >> anyFarOffPlaceFromDublin
        1 * mockDistanceCalculatorService.calculateDistanceInKilometers(dublin, dublin) >> 0.0
        1 * mockDistanceCalculatorService.calculateDistanceInKilometers(dublin, nearByDublin) >> 99.0
        1 * mockDistanceCalculatorService.calculateDistanceInKilometers(dublin, anyFarOffPlaceFromDublin) >> 110
        customers != null
        !customers.isEmpty()
        customers.size() == 2
        customers.get(0) == customer1
        customers.get(1) == customer2
    }
}
