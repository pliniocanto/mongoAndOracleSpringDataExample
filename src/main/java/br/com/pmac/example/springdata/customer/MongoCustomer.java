package br.com.pmac.example.springdata.customer;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Document(collection = "Customer")
@Getter
@RequiredArgsConstructor
@ToString
public class MongoCustomer {

	private @Id String id;
	private final String firstname, lastname;


}
