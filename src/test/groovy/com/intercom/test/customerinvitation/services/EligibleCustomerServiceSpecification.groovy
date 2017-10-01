package com.intercom.test.customerinvitation.services

import com.intercom.test.customerinvitation.entities.Customer
import spock.lang.Specification


class EligibleCustomerServiceSpecification extends Specification {

    def "service should return eligible customers lying within passed radius"() {
        given:
        EligibleCustomerService eligibleCustomerService = new EligibleCustomerService()
        double radiusInKms = 100

        when:
        List<Customer> customers = eligibleCustomerService.printEligibleCustomers(radiusInKms)

        then:
        customers != null
        !customers.isEmpty()
    }
}
