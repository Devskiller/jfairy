/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland.producer.text;

import eu.codearte.fairyland.producer.RandomProducer;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static eu.codearte.fairyland.producer.text.TextUtils.joinWithSpace;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.*;

public class TextProducer {

	private static final String DATA = "text";

	private final RandomProducer randomProducer;

	private final String loremIpsum;
	private final List<String> words;

	@Inject
	public TextProducer(RandomProducer randomProducer) {
		this.randomProducer = randomProducer;
		loremIpsum = randomProducer.getValue(DATA);
		words = asList(split(loremIpsum, ' '));
	}

	public String getLoremIpsum() {
		return loremIpsum;
	}

	public String rawWords(int count, int precision) {
		List<String> result = readRawWords(count, precision);
		return joinWithSpace(result);
	}

	public String cleanWords(int count) {
		List<String> result = newArrayList();
		for (String part : readRawWords(count, 0)) {
			result.add(uncapitalize(replaceChars(part, "., ", "")));
		}
		return joinWithSpace(result);
	}

	private List<String> readRawWords(int count, int precision) {
		return randomProducer.randomElements(words, getRandomWithPrecision(count, precision));
	}

	public int getRandomWithPrecision(int count, int precision) {
		return randomProducer.randomBetween(count, count + precision);
	}

}
