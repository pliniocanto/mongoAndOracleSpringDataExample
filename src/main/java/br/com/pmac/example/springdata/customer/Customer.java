package br.com.pmac.example.springdata.customer;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@RequiredArgsConstructor
@ToString
public class Customer {

	private @Id @GeneratedValue Long id;
	private final String firstname, lastname;

	Customer() {
		this.firstname = null;
		this.lastname = null;
	}

	public CustomerId getId() {
		return new CustomerId(id);
	}

	@Value
	@Embeddable
	@RequiredArgsConstructor
	@SuppressWarnings("serial")
	public static class CustomerId implements Serializable {

		private final Long customerId;

		CustomerId() {
			this.customerId = null;
		}
	}
}
