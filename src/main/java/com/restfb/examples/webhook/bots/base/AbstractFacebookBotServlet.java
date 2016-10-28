package com.restfb.examples.webhook.bots.base;

import com.restfb.examples.webhook.Config;
import org.eclipse.jetty.server.Response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class AbstractFacebookBotServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if ("subscribe".equals(req.getParameter("hub.mode")) //
        && Config.getInstance().getBotTokenVerify().equals(req.getParameter("hub.verify_token"))) {
      resp.getWriter().append(req.getParameter("hub.challenge"));
    } else {
      resp.setStatus(Response.SC_BAD_REQUEST);
    }
  }
}
