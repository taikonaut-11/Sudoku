
public class SudokuSolverEngine {

   public static void main(String[] args) {
      // Here I have called my class `MySudokuBoard` if you named your class
      // differently, modify the line below to use your own class name
      SudokuBoard board1 = new SudokuBoard("boards/very-fast-solve.sdk");
      SudokuBoard board2 = new SudokuBoard("boards/fast-solve.sdk");
      SudokuBoard board3 = new SudokuBoard("boards/valid-incomplete.sdk");
      testBoard(board3);
      
   }
   
   //pre: board is not null
   //post:input board is solved as pre and post steps are printed to the screen.
   public static void testBoard(SudokuBoard board) {
      System.out.println("Initial board state :");
      System.out.println(board);
      
      System.out.println();
      
      if(board.isSolved()) {
         System.out.print("Board is already solved.");
         return;
      } else if (!board.isValid()) {
         System.out.print("Cannot solve invalid board!");
         return;
      }

      System.out.print("Solving board...");
      long start = System.currentTimeMillis();
      board.solve();
      long stop = System.currentTimeMillis();
      System.out.printf("FINISHED in %.3f seconds.\n", ((stop-start)/1000.0));
      System.out.println("Is Solved? : " + board.isSolved());
      System.out.println("\nFinal board state :");
      System.out.println(board);
   }
}