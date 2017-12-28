package net.eikatou.demo.service;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.eikatou.demo.domain.Customer;
import net.eikatou.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository repository;
	
	public Customer save(Customer customer) {
//		return null;
		return this.repository.save(customer);
	}
	
	public List<Customer> findAll() {
		return repository.findAll();
	}
}
