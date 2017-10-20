package net.sample.animal.impl;

import net.sample.animal.Person;

public class PersonImpl implements Person {
	private String name;
	
	public PersonImpl(String name) {
		super();
		this.name = name;
	}

	@Override
	public void hello() {
		System.out.println(this.name + ":" + "hello.");
	}

	@Override
	public String getName() {
		return this.name;
	}

}
