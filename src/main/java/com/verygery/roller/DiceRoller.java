package com.verygery.roller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Rolls a number of dice.
 */
public class DiceRoller {

  /**
   * Rolls a number of dice and returns with the result.
   *
   * @param count
   *          The number of dice to roll.
   * @param sides
   *          The number of sides the dice have.
   * @return A list of rolled numbers.
   */
  public List<Integer> rollDice(int count, int sides) {
    List<Integer> result = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < count; i++) {
      result.add(random.nextInt(sides) + 1);
    }
    return result;
  }
}
