package io.codearte.jfairy.producer.company.locale.zh;

import com.google.inject.assistedinject.Assisted;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.CompanyProperties;
import io.codearte.jfairy.producer.company.DefaultCompanyProvider;
import io.codearte.jfairy.producer.util.TextUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

/**
 * io.codearte.jfairy.producer.company.locale.zh.ZhCompanyProvider
 *
 * @author lhfcws
 * @since 2017/3/10
 */
public class ZhCompanyProvider extends DefaultCompanyProvider {
	@Inject
	public ZhCompanyProvider(BaseProducer baseProducer, DataMaster dataMaster, VATIdentificationNumberProvider vatIdentificationNumberProvider, @Assisted CompanyProperties.CompanyProperty... companyProperties) {
		super(baseProducer, dataMaster, vatIdentificationNumberProvider, companyProperties);
	}

	/**
	 * In case of the illegal hostname characters in company name
	 * and truncate it if it is too long (length > 10) after escape
	 *
	 * It is compatible with other non-latin language and will not change the original result for latin language.
	 *
	 * P.S. Actually the best way for Chinese here is to use phonetic writing (so as Japanese or Korean),
	 * but in the original design this CompanyProvider class is not expected to be inherited,
	 * and writing if-else codes for specific language here is not good.
	 */
	@Override
	public void generateDomain() {
		if (domain != null) {
			return;
		}

		String host = TextUtils.stripAccents(StringUtils.strip(StringUtils.deleteWhitespace(name.toLowerCase()), ".").replace("/", ""));
		int len1 = host.length();
		host = StringEscapeUtils.escapeJava(host).replaceAll("\\\\u", "");
		int len2 = host.length();
		if (len2 > len1 && len2 > 10)
			host = host.substring(0, 10);

		domain = host + "." + dataMaster.getRandomValue(DOMAIN);
	}
}
