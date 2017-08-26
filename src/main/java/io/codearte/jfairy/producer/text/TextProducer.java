/*
 * Copyright (c) 2013 Codearte
 */
package io.codearte.jfairy.producer.text;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.util.TextUtils;

import static org.apache.commons.lang3.StringUtils.left;

public class TextProducer {

	private static final int DEFAULT_WORD_COUNT = 3;
	private static final int DEFAULT_WORD_COUNT_IN_SENTENCE = 3;
	private static final int DEFAULT_SENTENCE_COUNT = 3;
	private static final int SENTENCE_COUNT_PRECISION_MIN = 1;
	private static final int SENTENCE_COUNT_PRECISION_MAX = 3;
	private final TextProducerInternal textProducerInternal;
	private final BaseProducer baseProducer;
	private int limit = 0;

	@Inject
	public TextProducer(TextProducerInternal textProducerInternal, BaseProducer baseProducer) {
		this.textProducerInternal = textProducerInternal;
		this.baseProducer = baseProducer;
	}

	public TextProducer limitedTo(int limit) {
		this.limit = limit;
		return this;
	}

	public String loremIpsum() {
		return result(textProducerInternal.loremIpsum());
	}

	public String text() {
		return result(textProducerInternal.text());
	}

	public String word() {
		return result(word(DEFAULT_WORD_COUNT));
	}

	public String word(int count) {
		return result(textProducerInternal.cleanWords(count));
	}

	public String latinWord() {
		return result(latinWord(DEFAULT_WORD_COUNT));
	}

	public String latinWord(int count) {
		return result(textProducerInternal.cleanLatinWords(count));
	}

	public String latinSentence() {
		return result(latinSentence(DEFAULT_SENTENCE_COUNT));
	}

	public String latinSentence(int wordCount) {
		return result(textProducerInternal.latinSentence(wordCount));
	}

	public String sentence() {
		return result(sentence(DEFAULT_WORD_COUNT_IN_SENTENCE));
	}

	public String sentence(int wordCount) {
		return result(textProducerInternal.sentence(wordCount));
	}

	public String paragraph() {
		return result(paragraph(DEFAULT_SENTENCE_COUNT));
	}

	public String paragraph(int sentenceCount) {
		return result(TextUtils.joinWithSpace(sentences(sentenceCount +
				baseProducer.randomBetween(SENTENCE_COUNT_PRECISION_MIN, SENTENCE_COUNT_PRECISION_MAX))));
	}

	/**
	 * Generates random string with desired length
	 *
	 * @param charsCount string length
	 * @return random string
	 */
	public String randomString(int charsCount) {
		return textProducerInternal.randomString(charsCount);
	}

	private List<String> sentences(int sentenceCount) {
		List<String> sentences = new ArrayList<String>(sentenceCount);
		for (int i = 0; i < sentenceCount; i++) {
			sentences.add(sentence());
		}
		return sentences;
	}

	private String result(String result) {
		if (limit > 0) {
			return left(result, limit);
		} else {
			return result;
		}
	}
}
