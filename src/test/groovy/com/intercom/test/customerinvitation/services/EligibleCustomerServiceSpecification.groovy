package com.intercom.test.customerinvitation.services

import com.intercom.test.customerinvitation.entities.Coordinate
import com.intercom.test.customerinvitation.entities.Customer
import spock.lang.Specification


class EligibleCustomerServiceSpecification extends Specification {

    CustomerFileReaderService mockCustomerFileReaderService = Mock(CustomerFileReaderService)
    DistanceCalculatorService mockDistanceCalculatorService = Mock(DistanceCalculatorService)

    def "service should return eligible customers lying within passed radius"() {
        given:
        EligibleCustomerService eligibleCustomerService = new EligibleCustomerService(mockDistanceCalculatorService, mockCustomerFileReaderService)
        String path = "path"
        double radiusInKms = 100
        //London 51.5074° N, 0.1278° W
        def customer1 = Customer.builder().name("Vishal").userId("1").latitude(53.339428).longitude(-6.257664).build()
        def customer2 = Customer.builder().name("Vishal").userId("1").latitude(53.349428).longitude(-6.267664).build()
        def customerFarOffInLondon = Customer.builder().name("Vishal").userId("1").latitude(51.5074).longitude(0.1278).build()
        List<Customer> expectedListOfCustomers = [customer1, customer2, customerFarOffInLondon]
        //Dublin 53.339428, -6.257664.
        Coordinate dublin = Coordinate.builder().latitude(53.339428).longitude(-6.257664).build()

        when:
        List<Customer> customers = eligibleCustomerService.printEligibleCustomers(path, dublin, radiusInKms)

        then:
        1 * mockCustomerFileReaderService.readFile(_ as String) >> expectedListOfCustomers
        3 * mockDistanceCalculatorService.calculateDistanceInKilometers(dublin, _ as Coordinate)
        customers != null
        !customers.isEmpty()
    }
}
