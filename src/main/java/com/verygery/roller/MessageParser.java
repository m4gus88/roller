package com.verygery.roller;

import com.skype.ChatMessage;
import com.skype.SkypeException;

/**
 * Parses a Skype message and returns with a result.
 */
public interface MessageParser {

  /**
   * Parses a Skype message.
   *
   * @param message
   *          The Skype message to parse.
   * @return <code>true</code> if the message was properly parsed.
   * @throws SkypeException
   *           when connection is lost.
   */
  public boolean parse(ChatMessage message) throws SkypeException;

}
