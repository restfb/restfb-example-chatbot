package com.restfb.examples.webhook.bots.base;

import com.restfb.examples.webhook.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract public class AbstractFacebookBotServlet extends HttpServlet {

  private static final Logger LOGGER = LogManager.getLogger(AbstractFacebookBotServlet.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if ("subscribe".equals(req.getParameter("hub.mode")) //
            && Config.getInstance().getBotTokenVerify().equals(req.getParameter("hub.verify_token"))) {
      resp.getWriter().append(req.getParameter("hub.challenge"));
      LOGGER.info("Challenge send back to Facebook: {}", req.getParameter("hub.challenge"));
    } else {
      LOGGER.warn("Bad request received instead of valid subscription");
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
