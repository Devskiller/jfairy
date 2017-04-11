package io.codearte.jfairy.producer.company;

import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.util.TextUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

public interface CompanyProvider extends Provider<Company> {

	String DOMAIN = "domains";
	String COMPANY_SUFFIX = "companySuffixes";
	String COMPANY_NAME = "companyNames";
	String COMPANY_EMAIL = "companyEmails";

	@Override
	Company get();

	void generateName();

	void generateDomain();

	void generateEmail();

	void generateVatIdentificationNumber();

	void setName(String name);

	void setDomain(String domain);

	void setEmail(String email);

	void setVatIdentificationNumber(String vatIdentificationNumber);
}
