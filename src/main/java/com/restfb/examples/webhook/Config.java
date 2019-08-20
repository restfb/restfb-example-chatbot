/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restfb.examples.webhook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

  Properties properties = new Properties();

  private static final Logger LOGGER = LogManager.getLogger(Config.class);

  private Config() {
    try {
      InputStream configStream = Config.class.getResourceAsStream("config.properties");
      if (configStream != null) {
        properties.load(configStream);
      } else {
        LOGGER.warn("could not load config.properties");
      }
    } catch (IOException ioe) {
      LOGGER.warn("could not load config.properties", ioe);
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
}
