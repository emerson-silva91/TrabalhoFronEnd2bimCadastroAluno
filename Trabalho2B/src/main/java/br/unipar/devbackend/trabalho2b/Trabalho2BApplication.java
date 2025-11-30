package br.unipar.devbackend.trabalho2b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Trabalho2BApplication {

    public static void main(String[] args) {
        SpringApplication.run(Trabalho2BApplication.class, args);
    }
}
