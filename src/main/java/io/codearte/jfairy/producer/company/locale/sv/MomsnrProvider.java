package io.codearte.jfairy.producer.company.locale.sv;

import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberProvider;
import org.joda.time.DateTime;

import javax.inject.Inject;

import static io.codearte.jfairy.producer.person.NationalIdentificationNumberProperties.dateOfBirth;
import static io.codearte.jfairy.producer.person.locale.sv.PersonalIdentityNumberProvider.calculateChecksum;
import static java.lang.String.valueOf;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.StringUtils.leftPad;

/**
 * Swedish VAT Identification number (known as Momsnr or Momsnummer in Sweden)
 * <p>
 * https://en.wikipedia.org/wiki/VAT_identification_number
 */
public class MomsnrProvider implements VATIdentificationNumberProvider {

	private static final int MOMSNRNR_LENGTH = 14;
	private static final int ENSKILD_FIRMA_UPPER_AGE_LIMIT = 16;
	private static final int ENSKILD_FIRMA_LOWER_AGE_LIMIT = 100;
	private static final String SE = "SE";

	private final BaseProducer baseProducer;
	private final DateProducer dateProducer;
	private final NationalIdentificationNumberFactory nationalIdentificationNumberFactory;

	@Inject
	public MomsnrProvider(BaseProducer baseProducer, DateProducer dateProducer,
						  NationalIdentificationNumberFactory nationalIdentificationNumberFactory) {
		this.baseProducer = baseProducer;
		this.dateProducer = dateProducer;
		this.nationalIdentificationNumberFactory = nationalIdentificationNumberFactory;
	}

	@Override
	public String get() {
		boolean isEnskildFirma = baseProducer.trueOrFalse(); // Approximately 50% probablilty of a company to be of type enskild firma
		if (isEnskildFirma) {
			return generateMomsnrForEnskildFirma();
		}

		int randomGroupNumber = baseProducer.randomElement(GroupNumber.class).getValue();
		String randomNumericBetween20And99 = leftPad(valueOf(baseProducer.randomBetween(20, 99)), 2, "0");
		String organizationNumberWithoutChecksum = randomGroupNumber + randomNumeric(1) + randomNumericBetween20And99 + randomNumeric(5);
		String organizationNumber = organizationNumberWithoutChecksum + calculateChecksum(organizationNumberWithoutChecksum);

		return SE + organizationNumber + "01";
	}

	private String generateMomsnrForEnskildFirma() {
		DateTime lowerAgeLimit = DateTime.now().minusYears(ENSKILD_FIRMA_LOWER_AGE_LIMIT);
		DateTime upperAgeLimit = DateTime.now().minusYears(ENSKILD_FIRMA_UPPER_AGE_LIMIT);
		DateTime dateOfBirth = dateProducer.randomDateBetweenTwoDates(lowerAgeLimit, upperAgeLimit);
		NationalIdentificationNumberProvider nationalIdentificationNumberProvider = nationalIdentificationNumberFactory.produceNationalIdentificationNumberProvider(
				dateOfBirth(dateOfBirth));
		String personalIdentityNumber = nationalIdentificationNumberProvider.get().getValue();
		return SE + personalIdentityNumber.replace("-", "") + "01";
	}

	/**
	 * @param momsnr Moms nummer
	 * @return momsnr validity
	 */
	public static boolean isValid(String momsnr) {
		int size = momsnr.length();
		if (size != MOMSNRNR_LENGTH) {
			return false;
		}

		int checksum = Integer.valueOf(Character.toString(momsnr.charAt(size - 3)));
		int checkDigit = calculateChecksum(momsnr.substring(2, momsnr.length() - 2));

		return checkDigit == checksum;

	}

	private enum GroupNumber {
		DODSBON(1),
		STAT_LANDSTING_KOMMUNER_FORSAMLINGAR(2),
		UTLANDSKA_FORETAG(3),
		AKTIEBOLAG(5),
		ENKELT_BOLAG(6),
		EKONOMISKA_FORENINGAR(7),
		IDEELLA_FORENINGAR_OCH_STIFTELSER(8),
		HANDELSBOLAG_KOMMANDITBOLAG_OCH_ENKLA_BOLAG(9);

		private final int value;

		GroupNumber(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
