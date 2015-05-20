package io.codearte.jfairy.dataProvider.payment;

public interface IBANFactory {

	IBANProvider produceIBANProvider(IBANProperties.Property... properties);

}
