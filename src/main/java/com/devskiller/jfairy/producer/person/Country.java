package com.devskiller.jfairy.producer.person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.devskiller.jfairy.producer.util.CurrencyCode;
import com.devskiller.jfairy.producer.util.LanguageCode;

public enum Country {
	Poland("PL", CurrencyCode.PLN, LanguageCode.PL),
	UnitedKingdom("GB", CurrencyCode.GBP, LanguageCode.EN),
	Australia("AU", CurrencyCode.AUD, LanguageCode.EN),
	USA("US", CurrencyCode.USD, LanguageCode.EN),
	Canada("CA", CurrencyCode.CAD, LanguageCode.EN, LanguageCode.FR),
	Spain("ES", CurrencyCode.EUR, LanguageCode.ES),
	France("FR", CurrencyCode.EUR, LanguageCode.FR),
	Georgia("GE", CurrencyCode.GEL, LanguageCode.KA),
	Italy("IT", CurrencyCode.EUR, LanguageCode.IT),
	Germany("DE", CurrencyCode.EUR, LanguageCode.DE),
	Sweden("SE", CurrencyCode.SEK, LanguageCode.SV),
	China("CN", CurrencyCode.RMB, LanguageCode.ZH);

	//	ISO 3166 code
	private final String code;
	//	ISO 639-1
	private final List<LanguageCode> languages;
	//	ISO 4217
	private final CurrencyCode currencyCode;

	Country(String code, CurrencyCode currencyCode, LanguageCode... language) {
		this.code = code;
		this.currencyCode = currencyCode;
		this.languages = Arrays.asList(language);
	}

	public String getCode() {
		return code;
	}

	public static List<Country> findCountryForLanguage(LanguageCode language) {
		return Arrays.stream(Country.values())
			.filter(country -> country.languages.contains(language))
			.collect(Collectors.toList());
	}

	public String getCurrencyCode() {
		return currencyCode.name();
	}
}
