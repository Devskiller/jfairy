package io.codearte.jfairy.producer.payment;

public interface IBANFactory {

	IBANProvider produceIBANProvider(IBANProperties.Property... properties);

}
