// Silas Maas
// CS 143
// Long HW Sudoku reading files and pretty printing.
//
// This class will hold a Sudoku boards state, which is inputted as a file name in the constuctor. Also can get the current board state as a string. 


import java.util.*;
import java.io.*;

public class SudokuBoard {
   final int[][] boardState = new int[9][9];
   
   //pre:  fileName must be a real file.
   //post: File with name fileName has it data put into boardState in the form of integers from 0-9, or an error is printed is console if fileName is invalid.
   public SudokuBoard(String fileName) {
      try (Scanner input = new Scanner(new File(fileName))) {
         for(int y = 0; y < 9; y++) {
            String line = input.nextLine();
            for(int x = 0; x < 9; x++) {
               char cellState = line.charAt(x);
               if (cellState == '.') {
                  boardState[y][x] = 0;
               } else {
                  boardState[y][x] = Character.getNumericValue(cellState);
               }
            }
         }
      } catch (FileNotFoundException error) {
         System.out.println("Error opening file");
      }
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