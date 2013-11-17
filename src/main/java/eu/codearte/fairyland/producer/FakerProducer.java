package eu.codearte.fairyland.producer;

import com.google.inject.Inject;

class FakerProducer {

	private final RandomGenerator randomGenerator;

	@Inject
	FakerProducer(RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	public String letterify(String letterString) {
		return replaceSymbolWithCharsFromTo(letterString, '?', 'a', 'z');
	}

	public String numerify(String numberString) {
		return replaceSymbolWithCharsFromTo(numberString, '#', '0', '9');
	}

	public String bothify(String string) {
		return letterify(numerify(string));
	}

	private String replaceSymbolWithCharsFromTo(String string, char symbol, char from, char to) {
		StringBuilder result = new StringBuilder();
		for (char aChar : string.toCharArray()) {
			if (aChar == symbol) {
				result.append(randomGenerator.randomBetween(from, to));
			} else {
				result.append(aChar);
			}
		}
		return result.toString();
	}

}
