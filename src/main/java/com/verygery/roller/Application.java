package com.verygery.roller;

import com.skype.Skype;
import com.skype.SkypeException;

public class Application {

  public static void main(String[] args) throws SkypeException {
    Skype.setDaemon(false);
    MessageParserChain parser = new MessageParserChain();
    parser.addParser(new DiceRollParser());
    parser.addParser(new ChatCreatorParser());
    MessageParsingChatMessageListener listener = new MessageParsingChatMessageListener();
    listener.setMessageParser(parser);

    Skype.addChatMessageListener(listener);
  }

}
