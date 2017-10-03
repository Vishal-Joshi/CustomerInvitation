package com.intercom.test.customerinvitation.services;

import com.intercom.test.customerinvitation.CoordinateObjectFactory;
import com.intercom.test.customerinvitation.entities.Coordinate;
import com.intercom.test.customerinvitation.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EligibleCustomerService {

    private final DistanceCalculatorService distanceCalculatorService;
    private final CustomerFileReaderService customerFileReaderService;
    private final CoordinateObjectFactory coordinateObjectFactory;

    @Autowired
    public EligibleCustomerService(DistanceCalculatorService distanceCalculatorService,
                                   CustomerFileReaderService customerFileReaderService,
                                   CoordinateObjectFactory coordinateObjectFactory) {
        this.distanceCalculatorService = distanceCalculatorService;
        this.customerFileReaderService = customerFileReaderService;
        this.coordinateObjectFactory = coordinateObjectFactory;
    }

    public List<Customer> printEligibleCustomers(String path, Coordinate source, double radiusInKms) {
        try {
            return customerFileReaderService.readFile(path)
                    .stream()
                    .filter(customer -> distanceCalculatorService.calculateDistanceInKilometers(source, coordinateObjectFactory.createObject(customer.getLatitude(), customer.getLongitude())) < radiusInKms)
                    .sorted((Customer c1, Customer c2) -> c1.getUserId().compareTo(c2.getUserId()))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
