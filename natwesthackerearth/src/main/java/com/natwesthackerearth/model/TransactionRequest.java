package com.natwesthackerearth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionRequest {

	@JsonProperty("AccountNumber")
	private String accountNumber;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("Amount")
	private String amount;

	@JsonProperty("Currency")
	private String currency;

	@JsonProperty("AccountFrom")
	private String accountFrom;

	public TransactionRequest() {
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
