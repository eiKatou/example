package com.sample.rest.controller;

import com.sample.rest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class IndexController {
    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
//        Customer created = customerRepository.save(
//                new Customer(null, "Hidetoshi", "Dekisugi"));
//        System.out.println(created + "is created.");
        customerRepository.findAll()
                .forEach(System.out::println);

        return "Hello world";
    }

}
