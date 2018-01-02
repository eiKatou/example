package com.sample.SpringDataJPA_hello.controller;

import com.sample.SpringDataJPA_hello.entity.Customer;
import com.sample.SpringDataJPA_hello.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloController {
    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        Customer created = customerRepository.save(
                new Customer(null, "Hidetoshi", "Dekisugi"));
        System.out.println(created + "is created.");
        customerRepository.findAll()
                .forEach(System.out::println);

        return "Hello world";
    }

}
