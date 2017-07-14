package br.com.pmac.example.springdata.customer;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface MongoCustomerRepository extends CrudRepository<Customer, String> {

	List<Customer> findByLastname(String lastname, Sort sort);

}
