package com.verygery.roller;

import com.google.common.base.Optional;
import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;

public class MessageParsingChatMessageListener implements ChatMessageListener {

  private MessageParserChain messageParser;

  @Override
  public void chatMessageReceived(ChatMessage receivedChatMessage) throws SkypeException {
    parseMessage(receivedChatMessage);
  }

  @Override
  public void chatMessageSent(ChatMessage sentChatMessage) throws SkypeException {
    parseMessage(sentChatMessage);
  }

  public void setMessageParser(MessageParserChain messageParser) {
    this.messageParser = messageParser;
  }

  private void parseMessage(ChatMessage message) throws SkypeException {
    Optional<String> result = messageParser.parse(message);
    if (result.isPresent()) {
      message.getChat().send(result.get());
    }
  }

}
