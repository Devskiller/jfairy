package com.devskiller.jfairy.producer.person;

import com.google.inject.Provider;

public interface AddressProvider extends Provider<Address> {

	Address get();
}
