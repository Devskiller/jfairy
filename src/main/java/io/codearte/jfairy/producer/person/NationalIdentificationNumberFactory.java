package io.codearte.jfairy.producer.person;

public interface NationalIdentificationNumberFactory {

	NationalIdentificationNumberProvider produceNationalIdentificationNumberProvider(NationalIdentificationNumberProperties.Property... properties);

}
