package com.verygery.roller;

import com.google.common.base.Optional;
import com.skype.ChatMessage;
import com.skype.SkypeException;

public interface MessageParser {

  public Optional<String> parse(ChatMessage message) throws SkypeException;

}
