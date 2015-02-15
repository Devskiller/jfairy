package io.codearte.jfairy.producer.company;

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

	public String name() {
		return name;
	}

	public String url() {
		return "http://www." + domain;
	}

	public String email() {
		return email + "@" + domain;
	}

	public String domain() {
		return domain;
	}

	public String vatIdentificationNumber() {
		return vatIdentificationNumber;
	}


}
