package net.sample.animal.impl;

import net.sample.animal.Animal;

public class Cat implements Animal {
	private String name;
	
	public Cat(String name) {
		super();
		this.name = name;
	}

	@Override
	public void say() {
		System.out.println(this.name + ":Nya-!!");
	}

	@Override
	public String getName() {
		return this.name;
	}

}
