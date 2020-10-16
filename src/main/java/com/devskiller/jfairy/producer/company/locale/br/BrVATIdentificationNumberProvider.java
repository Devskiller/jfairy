package com.devskiller.jfairy.producer.company.locale.br;

import java.util.Set;

import com.google.common.collect.Sets;
import com.google.inject.Inject;

import com.devskiller.jfairy.producer.BaseProducer;
import com.devskiller.jfairy.producer.VATIdentificationNumberProvider;

import static java.lang.String.valueOf;
import static java.lang.System.arraycopy;
import static org.apache.commons.lang3.StringUtils.leftPad;

/**
 * Brazilian VAT Identification Number (known as the National Register of Legal Entities or CNPJ in Brazil)
 *
 * @author Nelson Rodrigues da Silva Júnior
 * @since 15.10.2020
 */
public class BrVATIdentificationNumberProvider implements VATIdentificationNumberProvider {

	private static final int EIN_LENGTH = 14;
	private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	private BaseProducer baseProducer;

	@Inject
	public BrVATIdentificationNumberProvider(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
	}

	@Override
	public String get() {
		char[] ein = new char[EIN_LENGTH];

		return valueOf(ein);
	}

	private int randomiza(int n) {
		int ranNum = (int) (Math.random() * n);
		return ranNum;
	}

	private int mod(int dividendo, int divisor) {
		return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
	}

	public String cnpj() {
		int n = 9;
		int n1 = randomiza(n);
		int n2 = randomiza(n);
		int n3 = randomiza(n);
		int n4 = randomiza(n);
		int n5 = randomiza(n);
		int n6 = randomiza(n);
		int n7 = randomiza(n);
		int n8 = randomiza(n);
		int n9 = 0; //randomiza(n);
		int n10 = 0; //randomiza(n);
		int n11 = 0; //randomiza(n);
		int n12 = 1; //randomiza(n);
		int d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5;

		d1 = 11 - (mod(d1, 11));

		if (d1 >= 10)
			d1 = 0;

		int d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6;

		d2 = 11 - (mod(d2, 11));

		if (d2 >= 10)
			d2 = 0;

		return "" + n1 + n2 + "." + n3 + n4 + n5 + "." + n6 + n7 + n8 + "/" + n9 + n10 + n11 + n12 + "-" + d1 + d2;
	}

	public boolean isValid(String cnpj) {
		cnpj = cnpj.trim().replace(".", "").replace("-", "");
		if ((cnpj==null)||(cnpj.length()!=14)) return false;

		Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
			soma += digito*peso[peso.length-str.length()+indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
}
