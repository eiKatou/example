package net.eikatou.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.eikatou.demo.domain.Calculator;
import net.eikatou.demo.domain.Customer;
import net.eikatou.demo.service.CustomerService;

@SpringBootApplication
public class SimpleAppApplication implements CommandLineRunner {

	@Autowired
	Calculator calculator;
	
	@Autowired
	CustomerService customerService;
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Simple Spring Boot.");
		if (args.length > 1) {
			System.out.println(args[1]);
		}
		if (args.length > 2) {
			System.out.println(args[2]);
		}
		
		int added = calculator.add(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		System.out.println(added);
		
		customerService.save(new Customer(1, "aaa", "bbb"));
	}
}
