package net.eikatou.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import net.eikatou.demo.domain.Customer;

@Repository
public class CustomerRepository {
	private final ConcurrentMap<Integer, Customer> customerMap
		= new ConcurrentHashMap<>();
	
	public List<Customer> findAll() {
		return new ArrayList<>(customerMap.values());
	}
	
	public Customer save(Customer customer) {
		customerMap.put(customer.getId(), customer);
		return customer;
	}
	// TODO:他にもメソッドを追加する。
}
