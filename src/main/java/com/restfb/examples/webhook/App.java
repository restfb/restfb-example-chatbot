package com.restfb.examples.webhook;

import com.restfb.examples.webhook.bots.EchoBotServlet;
import com.restfb.examples.webhook.bots.LogBotServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.InetSocketAddress;

public class App {
  public static void main(String[] args) throws Exception {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");

    Server jettyServer =
        new Server(new InetSocketAddress(Config.getInstance().getServerHost(), Config.getInstance().getServerPort()));

    jettyServer.setHandler(context);

    ServletHolder echoServlet = context.addServlet(EchoBotServlet.class, "/echo");
    echoServlet.setInitOrder(0);
    ServletHolder logServlet = context.addServlet(LogBotServlet.class, "/log");
    logServlet.setInitOrder(1);

    try {
      jettyServer.start();
      jettyServer.join();
    } finally {
      jettyServer.destroy();
    }
  }
}
