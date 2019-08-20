/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restfb.examples.webhook.bots;

import com.restfb.*;
import com.restfb.examples.webhook.Config;
import com.restfb.examples.webhook.bots.base.AbstractFacebookBotServlet;
import com.restfb.types.GraphResponse;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.Message;
import com.restfb.types.webhook.WebhookEntry;
import com.restfb.types.webhook.WebhookObject;
import com.restfb.types.webhook.messaging.MessagingItem;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EchoBotServlet extends AbstractFacebookBotServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final String body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

    JsonMapper mapper = new DefaultJsonMapper();
    WebhookObject whObject = mapper.toJavaObject(body, WebhookObject.class);

    for (final WebhookEntry entry : whObject.getEntryList()) {
      for (final MessagingItem item : entry.getMessaging()) {
        final String senderId = item.getSender().getId();
        Message simpleTextMessage = null;

        // create the echo message
        if (item.getMessage() != null && item.getMessage().getText() != null) {
          simpleTextMessage = new Message("Echo: " + item.getMessage().getText());
        }

        // build the recipient
        final IdMessageRecipient recipient = new IdMessageRecipient(senderId);

        // send response if it is a message and not send a "echo"
        if (simpleTextMessage != null && !item.getMessage().isEcho()) {
          final FacebookClient sendClient =
                  new DefaultFacebookClient(Config.getInstance().getBotTokenAccess(), Version.LATEST);
          sendClient.publish("me/messages", GraphResponse.class, Parameter.with("recipient", recipient),
                  Parameter.with("message", simpleTextMessage));
        }
      }
    }
  }

}