package org.jfairy.producer.company;

import com.google.inject.Provider;
import org.apache.commons.lang3.StringUtils;
import org.jfairy.data.DataMaster;
import org.jfairy.producer.BaseProducer;
import org.jfairy.producer.VATIdentificationNumber;

import javax.inject.Inject;

public class CompanyProvider implements Provider<Company> {

	private static final String DOMAIN = "domains";
	private static final String COMPANY_NAME = "companyNames";
	private static final String COMPANY_SUFFIX = "companySuffixes";
	private static final String COMPANY_EMAIL = "companyEmails";

	private BaseProducer baseProducer;
	private DataMaster dataMaster;

	private final VATIdentificationNumber vatIdentificationNumber;

	@Inject
	public CompanyProvider(BaseProducer baseProducer, DataMaster dataMaster, VATIdentificationNumber vatIdentificationNumber) {
		this.baseProducer = baseProducer;
		this.dataMaster = dataMaster;
		this.vatIdentificationNumber = vatIdentificationNumber;
	}


	@Override
	public Company get() {

		String name = dataMaster.getRandomValue(COMPANY_NAME);
		if (baseProducer.trueOrFalse()) {
			name += " " + dataMaster.getRandomValue(COMPANY_SUFFIX);
		}
		String domain = StringUtils.strip(StringUtils.deleteWhitespace(name.toLowerCase()), ".")
				+ "." + dataMaster.getRandomValue(DOMAIN);
		String email = dataMaster.getRandomValue(COMPANY_EMAIL);

		return new Company(name, domain, email, vatIdentificationNumber.generate());
	}
}
