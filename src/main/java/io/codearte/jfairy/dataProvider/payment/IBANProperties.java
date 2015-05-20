package io.codearte.jfairy.dataProvider.payment;

public final class IBANProperties {

	private IBANProperties() {
	}

	public abstract static class Property {

		public abstract void apply(IBANProvider provider);

	}

	public static Property branchCode(final String branchCode) {
		return new Property() {
			@Override
			public void apply(IBANProvider provider) {
				provider.setBranchCode(branchCode);
			}
		};
	}

	public static Property nationalCheckDigit(final String nationalCheckDigit) {
		return new Property() {
			@Override
			public void apply(IBANProvider provider) {
				provider.setNationalCheckDigit(nationalCheckDigit);
			}
		};
	}


	public static Property accountNumber(final String accountNumber) {
		return new Property() {
			@Override
			public void apply(IBANProvider provider) {
				provider.setAccountNumber(accountNumber);
			}
		};
	}


	public static Property country(final String country) {
		return new Property() {
			@Override
			public void apply(IBANProvider provider) {
				provider.setCountry(country);
			}
		};
	}

	public static Property bankCode(final String bankCode) {
		return new Property() {
			@Override
			public void apply(IBANProvider provider) {
				provider.setBankCode(bankCode);
			}
		};
	}


}
