package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;

import java.util.List;
import java.util.Random;

import static com.google.common.base.Joiner.on;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.split;

public class Text extends HookProducer {

  private static final String DATA = "text";
  private static Random random = new Random();

  private final String loremIpsum;
  private final List<String> words;

  public Text(DataMaster dataMaster) {
    super(dataMaster);
    loremIpsum = dataMaster.getAsOne(DATA);
    words = asList(split(loremIpsum, ' '));
  }

  public String loremIpsum() {
    return loremIpsum;
  }

  public String loremIpsum(int length) {
    if (loremIpsum.length() > length) {
      return loremIpsum.substring(0, length);
    } else {
      return loremIpsum;
    }
  }

  public String word() {
    return dataMaster.randomElement(words);
  }

  public String words(int count) {
    List<String> parts = dataMaster.randomElements(words, count);
    return on(" ").join(parts);
  }

  public String sentence(int wordCount) {
    int count = wordCount + random.nextInt(6);
    return capitalize(on(" ").join(words(count), "."));
  }
}
