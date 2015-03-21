package io.codearte.jfairy.producer.person;

import com.google.inject.Provider;


public interface NationalIdentityCardNumberProvider extends Provider<String> {

	String get();
}
