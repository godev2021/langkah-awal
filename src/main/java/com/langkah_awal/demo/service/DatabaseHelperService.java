package com.langkah_awal.demo.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class DatabaseHelperService {

    private final DataSource dataSource;

    public DatabaseHelperService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void resetDatabase() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("schema.sql"));
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("data.sql"));
        }
    }
}
