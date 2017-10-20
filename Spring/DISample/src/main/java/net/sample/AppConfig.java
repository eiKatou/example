package net.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.sample.animal.Animal;
import net.sample.animal.Person;
import net.sample.animal.impl.Cat;
import net.sample.animal.impl.PersonImpl;

@Configuration
@ComponentScan("net.sample.animal")
public class AppConfig {
	@Bean
	Person person() {
		return new PersonImpl("hanako");
	}
	
	@Bean
	Animal cat() {
		return new Cat("mike");
	}
	
	@Bean
	Animal cat2() {
		return new Cat("mike2");
	}
}
