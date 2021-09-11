package com.example.demo.service;

import com.example.demo.bean.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * References:
 * https://examples.javacodegeeks.com/spring-boot-download-csv-file-example/
 */
@Slf4j
@Data
@Service
public class CsvService {
    private static final String[] HEADERS = {"Id", "User name", "password", "Age"};
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT.withHeader(HEADERS);

    //load data into csv
    public ByteArrayInputStream load(final List<User> Users) {
        return writeDataToCsv(Users);
    }

    //write data to csv
    private ByteArrayInputStream writeDataToCsv(final List<User> users) {
        log.info("Writing data to the csv printer");

        try {
            //UTF-8 bom
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(new byte[]{(byte) 239, (byte) 187, (byte) 191});
            CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT);

            for (User user : users) {
                List<String> data = Arrays.asList(
                        String.valueOf(user.getId()),
                        user.getUsername(),
                        user.getPassword(),
                        String.valueOf(user.getAge()));

                printer.printRecord(data);
            }

            printer.flush();

            return new ByteArrayInputStream(stream.toByteArray());
        } catch (final IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }
}