package com.sample.SpringDataJPA_hello.controller;

import com.sample.SpringDataJPA_hello.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class PageController {
    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/page")
    @ResponseBody
    public String index() {
        System.out.println("page=0, size=2");
        Pageable pageable1 = PageRequest.of(0, 2);
        customerRepository.findAll(pageable1)
                .forEach(System.out::println);

        System.out.println("page=1, size=2");
        Pageable pageable2 = PageRequest.of(1, 2);
        customerRepository.findAll(pageable2)
                .forEach(System.out::println);

        return "/page";
    }

}
