package com.devskiller.jfairy.producer;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomGenerator {

	private final RandomDataGenerator randomDataGenerator;

	public RandomGenerator() {
		this.randomDataGenerator = new RandomDataGenerator(new JDKRandomGenerator());
	}

	public RandomGenerator(int seed) {
		this.randomDataGenerator = new RandomDataGenerator(new JDKRandomGenerator(seed));
	}

	public boolean nextBoolean() {
		return randomDataGenerator.getRandomGenerator().nextBoolean();
	}

	public <T> List<T> shuffle(List<T> elements) {
		Collections.shuffle(elements, (Random) randomDataGenerator.getRandomGenerator());
		return elements;
	}

	public int nextInt(int min, int max) {
		if (min == max) return min;
		return randomDataGenerator.nextInt(min, max);
	}

	public long nextDouble(long min, long max) {
		return randomDataGenerator.nextLong(min, max);
	}

	public double nextDouble(double min, double max) {
		return randomDataGenerator.nextUniform(min, max);
	}
}
