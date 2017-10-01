package com.intercom.test.customerinvitation.services

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
        customers.size() == 5
    }

    def "service should read a customer file and build correct customer objects with ids as read from file"() {
        given:
        CustomerFileReaderService customerFileReaderService = new CustomerFileReaderService()
        String path = this.getClass().getClassLoader().getResource("customer-test-file.txt").getPath()

        when:
        def customers = customerFileReaderService.readFile(path)

        then:
        //check user ids from file and in object is matching
        customers.find { it.userId == "12" } != null
        customers.find { it.userId == "1" } != null
        customers.find { it.userId == "2" } != null
        customers.find { it.userId == "3" } != null
        customers.find { it.userId == "28" } != null
    }

    def "service should skip null customer objects which are invalid customer json string"() {
        given:
        CustomerFileReaderService customerFileReaderService = new CustomerFileReaderService()
        String path = this.getClass().getClassLoader().getResource("customer-test-file-invalid.txt").getPath()

        when:
        def customers = customerFileReaderService.readFile(path)

        then:
        customers != null
        !customers.isEmpty()
        customers.size() == 4
    }
}