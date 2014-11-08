package com.verygery.roller;

import java.util.ArrayList;
import java.util.List;

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
      String[] args = message.getContent().split(" ");
      Chat chat = message.getChat();
      User[] members = chat.getAllMembers();
      List<String> userIds = new ArrayList<>();
      fetchChatUsers(members, userIds);
      for (int i = 1; i < args.length; i++) {
        userIds.add(args[i]);
      }
      String[] userIdArray = userIds.toArray(new String[0]);
      Skype.chat(userIdArray).send(message.getSenderDisplayName() + " created this chat with Roller.");
      chat.send(message.getSenderDisplayName() + " created a new chat.");
      result = true;
    }
    return result;
  }

  private void fetchChatUsers(User[] members, List<String> userIds) throws SkypeException {
    String me = Skype.getProfile().getId();
    for (int i = 0; i < members.length; i++) {
      String member = members[i].getId();
      if (!member.equals(me)) {
        userIds.add(member);
      }
    }
  }
}
