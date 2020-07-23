package com.ezhiyang.sdk.util;

/**
 * string utils
 * @author Theo Zhou
 *
 */
public class StringUtils {

  /**
   * string is not blank
   * @param cs
   * @return
   */
  public static boolean isNotBlank(final CharSequence cs) {
    return !StringUtils.isBlank(cs);
  }
  
  /**
   * string is blank
   * @param cs
   * @return
   */
  public static boolean isBlank(final CharSequence cs) {
    int strLen;
    if (cs == null || (strLen = cs.length()) == 0) {
        return true;
    }
    for (int i = 0; i < strLen; i++) {
        if (Character.isWhitespace(cs.charAt(i)) == false) {
            return false;
        }
    }
    return true;
}
  
}
