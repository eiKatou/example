package net.eikatou.demo.domain;

import org.springframework.stereotype.Component;

@Component
public class CalculatorImpl implements Calculator {

	@Override
	public int add(int arg1, int arg2) {
		return arg1 + arg2;
	}

}
