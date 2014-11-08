package com.verygery.roller;

import com.skype.Chat;
import com.skype.ChatMessage;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.User;

/**
 * Parses a Skype command for creating a new chat.
 */
public class ChatCreatorParser implements MessageParser {

  @Override
  public boolean parse(ChatMessage message) throws SkypeException {
    boolean result = false;
    if (message.getContent().startsWith("//chat")) {
      Chat chat = message.getChat();
      User[] members = chat.getAllMembers();
      String[] userIds = new String[members.length];
      String me = Skype.getProfile().getId();
      for (int i = 0; i < members.length; i++) {
        String member = members[i].getId();
        if (!member.equals(me)) {
          userIds[i] = member;
        }
      }
      Skype.chat(userIds).send(message.getSenderDisplayName() + " created this chat with Roller.");
      chat.send(message.getSenderDisplayName() + " created a new chat.");
      result = true;
    }
    return result;
  }
}
