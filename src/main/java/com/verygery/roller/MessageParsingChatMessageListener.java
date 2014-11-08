package com.verygery.roller;

import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;

/**
 * Listens to chat messages and runs a message parser for every received and
 * sent message.
 */
public class MessageParsingChatMessageListener implements ChatMessageListener {

  private MessageParser messageParser;

  @Override
  public void chatMessageReceived(ChatMessage receivedChatMessage) throws SkypeException {
    messageParser.parse(receivedChatMessage);
  }

  @Override
  public void chatMessageSent(ChatMessage sentChatMessage) throws SkypeException {
    messageParser.parse(sentChatMessage);
  }

  public void setMessageParser(MessageParserChain messageParser) {
    this.messageParser = messageParser;
  }

}
