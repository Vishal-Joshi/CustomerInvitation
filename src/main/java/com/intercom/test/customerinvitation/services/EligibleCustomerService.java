package com.intercom.test.customerinvitation.services;

import com.intercom.test.customerinvitation.factories.CoordinateObjectFactory;
import com.intercom.test.customerinvitation.entities.Coordinate;
import com.intercom.test.customerinvitation.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class should be used as the only
 * entry service to compute eligible customers.
 * @author Vishal Joshi
 */
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

    /**
     * This method computes the eligible customers lying within
     * the specified radius from the given source and sort them by {@link Customer#getUserId()}
     * @param path customer file path to read customers from
     * @param source source point to compute radius
     * @param radiusInKms maximum allowed radius for customers to be in
     * @return list of eligible customers sorted by userId
     * @throws IOException if file is not found at given path
     */
    public List<Customer> computeEligibleCustomers(String path, Coordinate source, double radiusInKms) throws IOException {

        return customerFileReaderService.readFile(path)
                .stream()
                .filter(customer -> distanceCalculatorService.calculateDistanceInKilometers(source, coordinateObjectFactory.createObject(customer.getLatitude(), customer.getLongitude())) < radiusInKms)
                .sorted((Customer c1, Customer c2) -> c1.getUserId().compareTo(c2.getUserId()))
                .collect(Collectors.toList());

    }
}
