package net.sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.sample.animal.Animal;
import net.sample.animal.Person;

public class Main {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Person person = context.getBean(Person.class);
		person.hello();
		
		Animal cat = context.getBean("cat", Animal.class);
		cat.say();
		
		Animal cat2 = context.getBean("cat2", Animal.class);
		cat2.say();
		
		Animal dog = context.getBean("dog", Animal.class);
		dog.say();
	}

}
