package com.verygery.roller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Optional;
import com.skype.ChatMessage;
import com.skype.SkypeException;

/**
 * Calls a chain of parsers to parse a command.
 */
public class MessageParserChain implements MessageParser {

  private static final String PREFIX = "//";

  private List<MessageParser> chain = new ArrayList<>();

  @Override
  public Optional<String> parse(ChatMessage message) throws SkypeException {
    Optional<String> result = Optional.absent();
    String content = message.getContent();
    if (content.startsWith(PREFIX)) {
      Iterator<MessageParser> iterator = chain.iterator();
      while (!result.isPresent() && iterator.hasNext()) {
        result = iterator.next().parse(message);
      }
    }
    return result;
  }

  public void addParser(MessageParser parser) {
    chain.add(parser);
  }

}
