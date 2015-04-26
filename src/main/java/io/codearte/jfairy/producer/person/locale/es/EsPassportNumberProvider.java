package io.codearte.jfairy.producer.person.locale.es;

import io.codearte.jfairy.producer.person.PassportNumberProvider;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author graux
 * @since 26/04/2015
 */
public class EsPassportNumberProvider implements PassportNumberProvider {
    @Override
    public String get() {
        return RandomStringUtils.randomAlphanumeric(9);
    }
}
