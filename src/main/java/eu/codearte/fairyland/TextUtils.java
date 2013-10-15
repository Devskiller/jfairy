/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland;

import java.util.List;

import static com.google.common.base.Joiner.on;

public final class TextUtils {

  public static String joinWithSpace(List<String> result) {
    return on(" ").join(result);
  }

}
