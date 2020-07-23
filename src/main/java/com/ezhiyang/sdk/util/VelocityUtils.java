package com.ezhiyang.sdk.util;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * velocity utils
 * @author ZY
 *
 */
public class VelocityUtils {
  
  private static Properties props = new Properties();

  
  static{
    props.setProperty(Velocity.INPUT_ENCODING,"UTF-8");
    props.setProperty(Velocity.RESOURCE_LOADER,"class");
    props.setProperty("class.resource.loader.class",
    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
  }

  public static String parseString(String uri,Map<String,Object> contextMap) {
    VelocityEngine engine = new VelocityEngine(props);
    VelocityContext context = new VelocityContext(contextMap);
    StringWriter sw = new StringWriter();
    engine.evaluate(context,sw,"Velocity",uri);
    return sw.toString();
  }
  
}
