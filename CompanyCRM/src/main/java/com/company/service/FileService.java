package com.company.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.company.model.Company;

import java.io.File;
import java.io.IOException;

public class FileService {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String FILE_PATH = "data/company.json";

    public static void saveCompany(Company company) throws IOException {
        File file = new File(FILE_PATH);
        File dir = file.getParentFile();

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(file, company);
    }

    public static Company loadCompany() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new Company();
        }
        return mapper.readValue(file, Company.class);
    }
}