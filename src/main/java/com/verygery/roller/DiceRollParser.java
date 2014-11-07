package com.verygery.roller;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Optional;
import com.skype.ChatMessage;
import com.skype.SkypeException;

public class DiceRollParser implements MessageParser {

  private static final Pattern PATTERN = Pattern.compile("\\/\\/([0-9]*)d([0-9]+)([+-][0-9]+)*");

  private DiceRoller diceRoller = new DiceRoller();

  @Override
  public Optional<String> parse(ChatMessage message) throws SkypeException {
    String result = null;
    Matcher matcher = PATTERN.matcher(message.getContent());
    if (matcher.matches()) {
      int count = 1;
      String countGroup = matcher.group(1);
      if (!countGroup.isEmpty()) {
        count = Integer.parseInt(countGroup);
      }
      int sides = Integer.parseInt(matcher.group(2));
      int bonus = 0;
      for (int i = 3; i <= matcher.groupCount(); i++) {
        String group = matcher.group(i);
        if (group != null && !group.isEmpty()) {
          bonus += Integer.parseInt(group);
        }
      }
      List<Integer> dice = diceRoller.rollDice(count, sides);

      StringBuilder sb = new StringBuilder();
      sb.append(message.getSenderDisplayName());
      sb.append(" rolled ");
      sb.append(count);
      sb.append("d");
      sb.append(sides);
      if (bonus != 0) {
        if (bonus > 0) {
          sb.append("+");
        }
        sb.append(bonus);
      }
      sb.append(": ");
      int total = bonus;
      Iterator<Integer> iterator = dice.iterator();
      while (iterator.hasNext()) {
        Integer die = iterator.next();
        total += die;
        sb.append(die);
        if (iterator.hasNext()) {
          sb.append(", ");
        }
      }
      sb.append("\nTotal: ");
      sb.append(total);

      result = sb.toString();
    }
    return Optional.fromNullable(result);
  }
}
