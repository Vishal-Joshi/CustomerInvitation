package com.intercom.test.customerinvitation;

import com.intercom.test.customerinvitation.entities.Coordinate;
import com.intercom.test.customerinvitation.services.EligibleCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
@Slf4j
public class Application {
    @Autowired
    private EligibleCustomerService eligibleCustomerService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.intercom.test.customerinvitation");

        if (args.length == 2) {
            String customerFilePath = args[0];

            try {
                double radiusInKms = Double.parseDouble(args[1]);
                if (!StringUtils.isEmpty(customerFilePath)) {
                    annotationConfigApplicationContext.getBean(Application.class).start(customerFilePath, radiusInKms);
                } else {
                    log.error("Customer file path cannot be empty!");
                }
            } catch (Exception exception) {
                log.error("Invalid radius value, value should be 'double'");
            }
        } else {
            log.error("Required params not provided!");
        }
    }

    private void start(String path, double radiusInKms) {
        //Dublin 53.339428, -6.257664
        Coordinate dublin = new Coordinate(53.339428, -6.257664);
        log.debug("File path received: " + path);
        try {
            eligibleCustomerService
                    .computeEligibleCustomers(path, dublin, radiusInKms)
                    .stream()
                    .forEach(customer -> log.info(customer.getName() + "-" + customer.getUserId()));
        } catch (IOException e) {
            log.error("Exception occurred: ", e);
        }
    }
}
