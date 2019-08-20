package com.restfb.examples.webhook.bots;

import com.restfb.examples.webhook.bots.base.AbstractFacebookBotServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class LogBotServlet extends AbstractFacebookBotServlet {

  private static final Logger LOGGER = LogManager.getLogger(LogBotServlet.class);

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

    LOGGER.info("Request Body is:\n{}", body);
  }
}
