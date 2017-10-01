package com.intercom.test.customerinvitation.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.test.customerinvitation.entity.Customer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CustomerFileReaderService {

    public List<Customer> readFile(String path) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return Files
                .lines(Paths.get(path))
                .map(line -> {
                    try {
                        return objectMapper.readValue(line, Customer.class);
                    } catch (IOException exception) {
                        log.error("Could not de-serialise json string to java object", exception);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}