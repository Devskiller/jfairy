package io.codearte.jfairy.producer.person;

import com.google.inject.Provider;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.util.TextUtils;
import org.apache.commons.lang3.StringUtils;

import static io.codearte.jfairy.producer.person.PersonProvider.PERSONAL_EMAIL;
import static org.apache.commons.lang3.StringUtils.lowerCase;

public class EmailProvider implements Provider<String> {

    private final DataMaster dataMaster;
    private final BaseProducer baseProducer;
    private final String firstName;
    private final String lastName;

    public EmailProvider(DataMaster dataMaster, BaseProducer baseProducer,
                         String firstName, String lastName) {
        this.dataMaster = dataMaster;
        this.baseProducer = baseProducer;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String get() {
        String prefix = "";
        switch (baseProducer.randomBetween(1, 3)) {
            case 1:
                prefix = StringUtils.replace(firstName + lastName, " ", "");
                break;
            case 2:
                prefix = StringUtils.replace(firstName + "." + lastName, " ", ".");
                break;
            case 3:
                prefix = StringUtils.replace(lastName, " ", "");
                break;
        }
        return TextUtils.stripAccents(lowerCase(prefix + '@' + dataMaster.getRandomValue(PERSONAL_EMAIL)));
    }
}
