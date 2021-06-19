package com.sample.rest.controller;

import com.sample.rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class IndexController {
    @Autowired
    CustomerService customerService;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
//        Customer created = customerRepository.save(
//                new Customer(null, "Hidetoshi", "Dekisugi"));
//        System.out.println(created + "is created.");
        customerService.findAll()
                .forEach(System.out::println);

        return "Hello world";
    }

}
