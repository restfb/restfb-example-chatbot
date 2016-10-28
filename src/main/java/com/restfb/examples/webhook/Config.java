/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restfb.examples.webhook;

import org.eclipse.jetty.util.log.Log;

import java.io.IOException;
import java.util.Properties;

public class Config {

  Properties properties = new Properties();

  private Config() {
    try {
      properties.load(Config.class.getResourceAsStream("config.properties"));
    } catch (IOException ioe) {
      Log.getLog().warn("could not load config.properties", ioe);
    }
  }

  private static class ConfigHolder {
    private static final Config INSTANCE = new Config();
  }

  public static Config getInstance() {
    return ConfigHolder.INSTANCE;
  }

  /**
   * get the verify token for the chatbot webhook
   * 
   * @return String representing the verify token
   */
  public String getBotTokenVerify() {
    return properties.getProperty("bot.token.verify");
  }

  /**
   * get the page access token for chatbot send api
   *
   * @return the page access token
   */
  public String getBotTokenAccess() {
    return properties.getProperty("bot.token.access");
  }

  /**
   * get the ip of the chatbot example server
   *
   * @return the host ip
   */
  public String getServerHost() {
    return properties.getProperty("server.host");
  }

  /**
   * get the port of the chatbot example server
   *
   * @return the port of the chatbot server as int
   */
  public int getServerPort() {
    return Integer.valueOf(properties.getProperty("server.port"));
  }
}
