package io.codearte.jfairy.producer.company;

public interface CompanyFactory {

	CompanyProvider produceCompany(CompanyProperties.CompanyProperty... companyProperties);

}
