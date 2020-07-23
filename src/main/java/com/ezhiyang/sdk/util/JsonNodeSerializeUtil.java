package com.ezhiyang.sdk.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NumericNode;
/**
 * json node seriaize util
 * @author ZY
 *
 */
public class JsonNodeSerializeUtil {

  public static List<String> toList(String signPropsStr) {
    List<String> signPropsOut = null;
    if (org.apache.commons.lang.StringUtils.isEmpty(signPropsStr)) {
      signPropsOut = Collections.emptyList();
    } else {
      signPropsOut = new ArrayList<>();
      StringBuilder wordBuilder = new StringBuilder();
      for (int i = 0; i < signPropsStr.length(); i++) {
        Character c = signPropsStr.charAt(i);
        if (Character.isWhitespace(c)) {
          continue;
        } else if (Character.isJavaIdentifierPart(c)) {
          wordBuilder.append(c);
        } else {
          if (wordBuilder.length() > 0) {
            signPropsOut.add(wordBuilder.toString());
            wordBuilder.setLength(0);
          }
          if (c == '[' || c == ']') {
            signPropsOut.add(c.toString());
          }
            
        }
      }
      if (wordBuilder.length() > 0) {
        signPropsOut.add(wordBuilder.toString());
        wordBuilder.setLength(0);
      }
    }
    return signPropsOut;
  }

  public static void jsonNodeToStr(StringBuilder builder, JsonNode dataNode, String props) {
    jsonNodeToStr(builder, dataNode, toList(props));
  }

  public static void jsonNodeToStr(StringBuilder builder, JsonNode dataNode, List<String> props) {
    jsonNodeToStr(builder, dataNode, props, false);
  }

  public static boolean jsonNodeToStr(StringBuilder builder, JsonNode dataNode, List<String> props,
      boolean started) {
    Stack<JsonNode> nodeStack = new Stack<>();
    boolean arrayPropStarted = false;
    boolean arrayPropStartSign = false;

    List<String> arrayPropList = null;
    ArrayNode arrayNode = null;
    for (String prop : props) {
      if ("[".equals(prop)) {
        arrayPropStartSign = true;
        continue;
      } else if ("]".equals(prop)) {
        arrayPropStarted = false;
        if (arrayNode != null) {
          started = arrayNodetoStr(builder, arrayNode, arrayPropList, started);
          dataNode = nodeStack.pop();
        }
        continue;
      }
      if (arrayPropStartSign) {
        nodeStack.push(dataNode);
        dataNode = dataNode.get(prop);
        arrayPropList = new ArrayList<>();
        arrayNode = (ArrayNode) dataNode;
        arrayPropStarted = true;
        arrayPropStartSign = false;
        continue;
      } else if (arrayPropStarted) {
        arrayPropList.add(prop);
        continue;
      }
      JsonNode propNode = dataNode.get(prop);
      if (started) {
        builder.append("&");
      } else {
        started = true;
      }

      String nodeValue = null;
      if (propNode instanceof NumericNode) {
        nodeValue = format(propNode.decimalValue(), "#.#####");
      } else {
        nodeValue = propNode == null ? "" : propNode.asText("");
      }

      builder.append(prop).append("=").append(nodeValue);
    }
    return started;
  }

  public static boolean arrayNodetoStr(StringBuilder builder, ArrayNode arrayNode,
      List<String> props, boolean started) {
    for (int i = 0; i < arrayNode.size(); i++) {
      JsonNode jsonNode = arrayNode.get(i);
      started = jsonNodeToStr(builder, jsonNode, props, started);
    }
    return started;
  }
  
  public static String format(Object number, String pattern) {
    DecimalFormat decimalFormat = new DecimalFormat(pattern);
    return decimalFormat.format(number);
  }

}
