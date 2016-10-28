package com.restfb.examples.webhook.bots;

import com.restfb.examples.webhook.bots.base.AbstractFacebookBotServlet;

import org.eclipse.jetty.util.log.Log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogBotServlet extends AbstractFacebookBotServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final String body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

    // log the received body
    Log.getLog().info("Log: \n" + body);
  }
}
