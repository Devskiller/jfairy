package eu.codearte.fairyland.producer;/*
 * Copyright (c) 2013. Codearte
 */

import java.util.List;

import static com.google.common.base.Joiner.on;

public final class TextUtils {
  public static String joinWithSpace(List<String> result) {
    return on(" ").join(result);
  }
}
