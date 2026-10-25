// Silas Maas
// CS 143
// Long HW Sudoku reading files and pretty printing.
//
// This program will test the SudokuBoard class and its ability to print using the toString method.


public class PlaySudoku {

   //post: A SudokuBoard is created from the data in data1.sdk which is then printed to the console.
   public static void main(String[] args) {
      SudokuBoard board1 = new SudokuBoard("data1.sdk");
      System.out.println(board1);
   }
}

/*
  ----jGRASP exec: java PlaySudoku
 +---+---+---+
 |2◦◦|1◦5|◦◦3|
 |◦54|◦◦◦|71◦|
 |◦1◦|2◦3|◦8◦|
 +---+---+---+
 |6◦2|8◦7|3◦4|
 |◦◦◦|◦◦◦|◦◦◦|
 |1◦5|3◦9|8◦6|
 +---+---+---+
 |◦2◦|7◦1|◦6◦|
 |◦81|◦◦◦|24◦|
 |7◦◦|4◦2|◦◦1|
 +---+---+---+
 
  ----jGRASP: Operation complete.
*/