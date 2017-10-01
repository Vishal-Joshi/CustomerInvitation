package com.intercom.test.customerinvitation.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.test.customerinvitation.entity.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerFileReaderService {

    public List<Customer> readFile(String path) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return Files
                .lines(Paths.get(path))
                .map(line -> {
                    try {
                        return objectMapper.readValue(line, Customer.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}