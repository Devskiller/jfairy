package com.devskiller.jfairy.producer.company;

public class Company {

	private final String name;
	private final String domain;
	private final String email;
	private final String vatIdentificationNumber;

	public Company(String name, String domain, String email, String vatIdentificationNumber) {
		this.name = name;
		this.domain = domain;
		this.email = email;
		this.vatIdentificationNumber = vatIdentificationNumber;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return "http://www." + domain;
	}

	public String getEmail() {
		return email + "@" + domain;
	}

	public String getDomain() {
		return domain;
	}

	public String getVatIdentificationNumber() {
		return vatIdentificationNumber;
	}

}
