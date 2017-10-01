package com.intercom.test.customerinvitation.reader

import spock.lang.Specification

class CustomerFileReaderServiceSpecification extends Specification {

    def "service should read a customer file from given path successfully"() {
        given:
        CustomerFileReaderService customerFileReaderService = new CustomerFileReaderService()
        String path = this.getClass().getClassLoader().getResource("customer-test-file.txt").getPath()

        when:
        def customers = customerFileReaderService.readFile(path)

        then:
        customers != null
        !customers.isEmpty()
    }
}
