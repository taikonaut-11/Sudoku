// Silas Maas
// CS 143
// Long HW Sudoku validating board with maps and sets
//
// This class will hold a Sudoku boards state, now with the ability to validate if its state is valid or solved.

import java.util.*;
import java.io.*;

public class SudokuBoard {
   private final int[][] boardState = new int[9][9];
   private final char[] validChars = {'.', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
   
   //pre:  fileName must be a real file.
   //post: File with name fileName has it data put into boardState in the form of integers from 0-9, or an error is printed is console if fileName is invalid.
   public SudokuBoard(String fileName) {
      try (Scanner input = new Scanner(new File(fileName))) {
         for(int y = 0; y < 9; y++) {
            String line = input.nextLine();
            for(int x = 0; x < 9; x++) {
               char cellChar = line.charAt(x);
               int cellNum = -1;
               for (char validChar : validChars) {
                  if (cellChar == validChar) {
                     if (cellChar == '.') {
                        cellNum = 0;
                     } else {
                        cellNum = Character.getNumericValue(cellChar);
                     }
                  }
               }
               boardState[y][x] = cellNum;
            }
         }
      } catch (FileNotFoundException error) {
         System.out.println("Error opening file");
      }
   }   
   
   //pre:  none
   //post: returns true if all cells have valid states (0-9)
   private boolean checkCellData() {
      for (int y = 0; y < 9; y++) {
         for (int x = 0; x < 9; x++) {
            int cellVal = boardState[y][x];
            if (cellVal > 9 || cellVal < 0) {
               return false;
            }
         }
      }
      return true;
   }

   //pre:  none
   //post: returns true if all rows to not contain repeat numbers, excluding 0.
   private boolean checkRows() {
      Set<Integer> cellSet = new HashSet<>();
      for (int y = 0; y < 9; y++) {
         for (int x = 0; x < 9; x++) {
            int cellVal = boardState[y][x];
            if (cellSet.contains(cellVal)) {
               return false;
            }
            if (cellVal != 0) {
               cellSet.add(cellVal);
            }
         }
         cellSet.clear();
      }
      return true;
   }

   //pre:  none
   //post: returns true if all collumns to not contain repeat numbers, excluding 0.
   private boolean checkColumns() {
      Set<Integer> cellSet = new HashSet<>();
      for (int x = 0; x < 9; x++) {
         for (int y = 0; y < 9; y++) {
            int cellVal = boardState[y][x];
            if (cellSet.contains(cellVal)) {
               return false;
            }
            if (cellVal != 0) {
               cellSet.add(cellVal);
            }
         }
         cellSet.clear();
      }
      return true;
   }
   
   //pre:  inputted spot is 1-9
   //post: returns double int array miniSquare based on input, right -> left, top -> bottom.
   private int[][] getMiniSquare(int spot) {
      int[][] mini = new int[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            // whoa - wild! This took me a solid hour to figure out (at least)
            // This translates between the "spot" in the 9x9 Sudoku board
            // and a new mini square of 3x3
            mini[r][c] = boardState[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
      return mini;
   }
   
   //Pre:  none
   //Post: Returns true if all minisquares are valid, returns false if not.
   private boolean checkMiniSquares() {
      Set<Integer> cellSet = new HashSet<>();
      for (int s = 1; s <= 9; s++) {
         int[][] mini = getMiniSquare(s);
         for (int i = 0; i < 9; i++) {
            int cellVal = mini[i/3][i % 3];
            if (cellSet.contains(cellVal)) {
               return false;
            }
            if (cellVal != 0) {
               cellSet.add(cellVal);
            }
         }
         cellSet.clear();
      }
      return true;
   }


   //pre:  none
   //post: returns true if all Sudoku rules are followed for cell number placements, false if not.
   public boolean isValid() {
      return checkRows() && checkColumns() && checkMiniSquares();
   }

   //Pre: None.
   //post: returns true if all sudoku rules are followed and numbers 1-9 each appear 9 times, false if either condition is false.
   public boolean isSolved() {
      if (!isValid()) {
         return false;
      }
      HashMap<Integer, Integer> numMap = new HashMap<>();
      for (int i = 0; i < 81; i++) {
         int cellVal = boardState[i / 9][i % 9];
         if (!numMap.containsKey(cellVal)){
            numMap.put(cellVal,1);
         } else {
            numMap.put(cellVal, numMap.get(cellVal) + 1);
         }
      }
      for (int i = 1; i <= 9; i++) {
         if (!numMap.containsKey(i)) {
            return false;
         } else if (numMap.get(i) != 9) {
            return false;  
         }
      }
      return true;
   }
   
   
   
   
   
   //pre:  None
   //post: String showing boardState is returned.
   public String toString() {
      String boardStr = "";
      for(int y = 0; y < 9; y++) {
         if (y % 3 == 0) {
            boardStr += "+---+---+---+\n";
         }
         for(int x = 0; x < 9; x++) {
            if (x % 3 == 0) {
               boardStr += '|';
            }
            int cellVal = boardState[y][x];
            if (cellVal == 0) {
               boardStr += '◦';
            } else {
               boardStr += cellVal;
            }
         }
         boardStr += "|\n";
      }
      boardStr += "+---+---+---+";
      return boardStr;
   }
}