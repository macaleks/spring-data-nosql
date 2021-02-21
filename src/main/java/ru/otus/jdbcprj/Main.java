package ru.otus.jdbcprj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Main {


    public static void main(String[] args) throws InterruptedException, SQLException {
        SpringApplication.run(Main.class);
    }

}
