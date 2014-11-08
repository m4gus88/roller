package com.verygery.roller;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.skype.ChatMessage;
import com.skype.SkypeException;

/**
 * Parses a Skype command for rolling dice.
 */
public class DiceRollParser implements MessageParser {

  private static final Pattern PATTERN = Pattern.compile("\\/\\/([0-9]*)d([0-9]+)([+-][0-9]+)*");

  private DiceRoller diceRoller = new DiceRoller();

  @Override
  public boolean parse(ChatMessage message) throws SkypeException {
    boolean result = false;
    Matcher matcher = PATTERN.matcher(message.getContent());
    if (matcher.matches()) {
      int count = fetchCount(matcher);
      int sides = fetSides(matcher);
      int bonus = fetchBonus(matcher);
      List<Integer> dice = diceRoller.rollDice(count, sides);

      String sender = message.getSenderDisplayName();
      message.getChat().send(buildResponse(count, sides, bonus, dice, sender));
      result = true;
    }
    return result;
  }

  private int fetchCount(Matcher matcher) {
    int count = 1;
    String countGroup = matcher.group(1);
    if (countGroup != null && !countGroup.isEmpty()) {
      count = Integer.parseInt(countGroup);
    }
    return count;
  }

  private int fetSides(Matcher matcher) {
    return Integer.parseInt(matcher.group(2));
  }

  private int fetchBonus(Matcher matcher) {
    int bonus = 0;
    for (int i = 3; i <= matcher.groupCount(); i++) {
      String group = matcher.group(i);
      if (group != null && !group.isEmpty()) {
        bonus += Integer.parseInt(group);
      }
    }
    return bonus;
  }

  private String buildResponse(int count, int sides, int bonus, List<Integer> dice, String sender) {
    String result;
    StringBuilder sb = new StringBuilder();
    sb.append(sender);
    sb.append(" rolled ");
    sb.append(count);
    sb.append("d");
    sb.append(sides);
    appendBonus(sb, bonus);
    sb.append(": ");
    appendDiceResults(sb, dice, bonus);
    result = sb.toString();
    return result;
  }

  private void appendBonus(StringBuilder sb, int bonus) {
    if (bonus != 0) {
      if (bonus > 0) {
        sb.append("+");
      }
      sb.append(bonus);
    }
  }

  private void appendDiceResults(StringBuilder sb, List<Integer> dice, int bonus) {
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
  }
}
