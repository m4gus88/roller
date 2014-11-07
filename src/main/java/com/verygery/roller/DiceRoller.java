package com.verygery.roller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceRoller {

  public List<Integer> rollDice(int count, int sides) {
    List<Integer> result = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < count; i++) {
      result.add(random.nextInt(sides) + 1);
    }
    return result;
  }
}
