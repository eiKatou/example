package net.sample.animal.impl;

import org.springframework.stereotype.Component;

import net.sample.animal.Animal;

@Component("dog")
public class Dog implements Animal {
	private String name;
	
	public Dog() {
		this.name = "taro";
	}

	@Override
	public void say() {
		System.out.println(this.name + ":Wan!");
	}

	@Override
	public String getName() {
		return this.name;
	}

}
