package io.codearte.jfairy.producer.company;

import com.google.inject.assistedinject.Assisted;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.util.TextUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

public class DefaultCompanyProvider implements CompanyProvider {

	protected String name;
	protected String domain;
	protected String email;
	protected String vatIdentificationNumber;

	protected BaseProducer baseProducer;
	protected DataMaster dataMaster;

	protected VATIdentificationNumberProvider vatIdentificationNumberProvider;

	@Inject
	public DefaultCompanyProvider(BaseProducer baseProducer,
								  DataMaster dataMaster,
								  VATIdentificationNumberProvider vatIdentificationNumberProvider,
								  @Assisted CompanyProperties.CompanyProperty... companyProperties) {
		this.baseProducer = baseProducer;
		this.dataMaster = dataMaster;
		this.vatIdentificationNumberProvider = vatIdentificationNumberProvider;

		for (CompanyProperties.CompanyProperty companyProperty : companyProperties) {
			companyProperty.apply(this);
		}
	}

	@Override
	public Company get() {

		generateName();
		generateDomain();
		generateEmail();
		generateVatIdentificationNumber();

		return new Company(name, domain, email, vatIdentificationNumber);
	}

	@Override
	public void generateName() {
		if (name != null) {
			return;
		}
		name = dataMaster.getRandomValue(COMPANY_NAME);
		if (baseProducer.trueOrFalse()) {
			name += " " + dataMaster.getRandomValue(COMPANY_SUFFIX);
		}
	}

	@Override
	public void generateDomain() {
		if (domain != null) {
			return;
		}
		domain = TextUtils.stripAccents(StringUtils.strip(StringUtils.deleteWhitespace(name.toLowerCase()), ".").replace("/", ""))
				+ "." + dataMaster.getRandomValue(DOMAIN);
	}

	@Override
	public void generateEmail() {
		if (email != null) {
			return;
		}
		email = dataMaster.getRandomValue(COMPANY_EMAIL);
	}

	@Override
	public void generateVatIdentificationNumber() {
		if (vatIdentificationNumber != null) {
			return;
		}
		vatIdentificationNumber = vatIdentificationNumberProvider.get();
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setVatIdentificationNumber(String vatIdentificationNumber) {
		this.vatIdentificationNumber = vatIdentificationNumber;
	}
}
