package com.intercom.test.customerinvitation;

import com.intercom.test.customerinvitation.entities.Coordinate;
import com.intercom.test.customerinvitation.services.EligibleCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Application {
    @Autowired
    private EligibleCustomerService eligibleCustomerService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.intercom.test.customerinvitation");
        annotationConfigApplicationContext.getBean(Application.class).start();
    }

    private void start() {
        //Dublin 53.339428, -6.257664
        Coordinate dublin = new Coordinate(53.339428, -6.257664);
        double radiusInKms = 100;
        String path = this.getClass().getClassLoader().getResource("customer-file.txt").getPath();

        try {
            eligibleCustomerService
                    .computeEligibleCustomers(path, dublin, radiusInKms)
                    .stream()
                    .forEach(customer -> System.out.println(customer.getName() + "-" + customer.getUserId()));
        } catch (IOException e) {
            System.out.print("Exception occurred" + e);
        }
    }
}
