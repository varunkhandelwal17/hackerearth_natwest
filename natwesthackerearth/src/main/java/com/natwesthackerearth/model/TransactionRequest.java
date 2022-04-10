package com.natwesthackerearth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "transaction_requests")
public class TransactionRequest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonProperty("AccountNumber")
	@Column(name = "accountNumber")
	private String accountNumber;

	@JsonProperty("Type")
	@Column(name = "type")
	private String type;

	@JsonProperty("Amount")
	@Column(name = "amount")
	private String amount;

	@JsonProperty("Currency")
	@Column(name = "currency")
	private String currency;

	@JsonProperty("AccountFrom")
	@Column(name = "accountFrom")
	private String accountFrom;

	public TransactionRequest() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(String accountFrom) {
		this.accountFrom = accountFrom;
	}

}
