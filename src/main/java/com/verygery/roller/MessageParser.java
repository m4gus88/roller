package com.verygery.roller;

import com.google.common.base.Optional;
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
   * @return The result to send back.
   * @throws SkypeException
   *           when connection is lost.
   */
  public Optional<String> parse(ChatMessage message) throws SkypeException;

}
