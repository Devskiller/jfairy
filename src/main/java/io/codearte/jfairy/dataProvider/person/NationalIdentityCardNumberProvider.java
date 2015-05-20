package io.codearte.jfairy.dataProvider.person;

import com.google.inject.Provider;


public interface NationalIdentityCardNumberProvider extends Provider<String> {

	String get();
}
