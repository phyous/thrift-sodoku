package com.phyous.sodoku;

import com.phyous.sodoku.thrift.BadArgumentException;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: pyoussef
 * Date: 10/18/12
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class SodokuPuzzle {
  private int[][] data;

  public SodokuPuzzle(String input) throws BadArgumentException {
    if (input.length() != 81)
      throw new IllegalArgumentException("puzzle data must be of length 81 (9x9)");

    data = new int[9][9];
    char[] characters = input.toCharArray();
    int tmp;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        tmp = characters[j * 9 + i];
        if (tmp < 48 || tmp > 57)
          throw new BadArgumentException("Illegal character detected in input string");
        data[i][j] = tmp - 48;
      }
    }
  }

  /**
   * Converts internal 2-d array representation of sodoku puzzle to a 81 character string representation
   *
   * @return String representing sodoku puzzle
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        sb.append(data[i][j]);
      }
    }
    return sb.toString();
  }

  public String solve() throws BadArgumentException{
    if(!solvePuzzle(0,0))
      throw new BadArgumentException("problem provided is unsolvable");

    return this.toString();
  }

  private boolean solvePuzzle(int row, int col) {
    if (row > 8)
      return true;

    if (data[row][col] != 0)
      return nextSol(row, col);
    else {
      for (int i = 1; i <= 9; i++) {
        if (checkRow(row, i) && checkCol(col, i) && checkBox(row, col, i)) {
          data[row][col] = i;
          if (nextSol(row, col)) {
            return true;
          }
        }
      }
      data[row][col] = 0;
      return false;
    }
  }

  private boolean nextSol(int row, int col) {
    if (col == 8)
      return solvePuzzle(row + 1, 0);
    else
      return solvePuzzle(row, col + 1);
  }

  private boolean checkRow(int row, int toAdd) {
    HashSet<Integer> nums = new HashSet<Integer>();
    nums.add(toAdd);
    int tmp;
    for (int i = 0; i < 9; i++) {
      tmp = data[row][i];
      if (tmp!=0 && nums.contains(tmp))
        return false;
      else
        nums.add(tmp);
    }
    return true;
  }

  private boolean checkCol(int col, int toAdd) {
    HashSet<Integer> nums = new HashSet<Integer>();
    nums.add(toAdd);
    int tmp;
    for (int i = 0; i < 9; i++) {
      tmp = data[i][col];
      if (tmp!=0 && nums.contains(tmp))
        return false;
      else
        nums.add(tmp);
    }
    return true;
  }

  private boolean checkBox(int x, int y, int toAdd) {
    int xBox = x / 3;
    int yBox = y / 3;
    HashSet<Integer> nums = new HashSet<Integer>();
    nums.add(toAdd);

    int tmp;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        tmp = data[i + xBox * 3][j + yBox * 3];
        if (tmp!=0 && nums.contains(tmp))
          return false;
        else
          nums.add(tmp);
      }
    }
    return true;
  }
}
