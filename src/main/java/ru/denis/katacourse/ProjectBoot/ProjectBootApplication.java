package ru.denis.katacourse.ProjectBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProjectBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectBootApplication.class, args);
    }

}
