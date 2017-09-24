package net.eikatou.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleAppApplication implements CommandLineRunner {

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
	}
}
