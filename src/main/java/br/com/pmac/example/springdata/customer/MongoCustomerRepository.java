package br.com.pmac.example.springdata.customer;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface MongoCustomerRepository extends CrudRepository<MongoCustomer, String> {

	List<MongoCustomer> findByLastname(String lastname, Sort sort);

}
