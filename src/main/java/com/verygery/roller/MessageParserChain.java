package com.verygery.roller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.skype.ChatMessage;
import com.skype.SkypeException;

/**
 * Calls a chain of parsers to parse a command.
 */
public class MessageParserChain implements MessageParser {

  private static final String PREFIX = "//";

  private List<MessageParser> chain = new ArrayList<>();

  @Override
  public boolean parse(ChatMessage message) throws SkypeException {
    String content = message.getContent();
    if (content.startsWith(PREFIX)) {
      Iterator<MessageParser> iterator = chain.iterator();
      boolean parsed = false;
      while (!parsed && iterator.hasNext()) {
        parsed = iterator.next().parse(message);
      }
    }
    return true;
  }

  public void addParser(MessageParser parser) {
    chain.add(parser);
  }

}
