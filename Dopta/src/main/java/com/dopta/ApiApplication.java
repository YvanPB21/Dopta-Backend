package com.dopta;

import com.dopta.model.User;
import com.dopta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ApiApplication {
    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(1, "yvan@upc", "yvanpb", "navy"),
                new User(2, "ivan@upc", "ivanfake", "navi")

        ).collect(Collectors.toList());
        repository.saveAll(users);
    }
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
