package com.intercom.test.customerinvitation.services;

import com.intercom.test.customerinvitation.entities.Coordinate;
import com.intercom.test.customerinvitation.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EligibleCustomerService {

    private final DistanceCalculatorService distanceCalculatorService;
    private final CustomerFileReaderService customerFileReaderService;

    @Autowired
    public EligibleCustomerService(DistanceCalculatorService distanceCalculatorService,
                                   CustomerFileReaderService customerFileReaderService) {
        this.distanceCalculatorService = distanceCalculatorService;
        this.customerFileReaderService = customerFileReaderService;
    }

    public List<Customer> printEligibleCustomers(String path, Coordinate source, double radiusInKms) {
        try {
            List<Customer> customers = customerFileReaderService.readFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
