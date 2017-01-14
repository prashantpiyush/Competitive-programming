// Sudoku.java
/**
* This program takes a 9x9 sudoku as input and
* prints its solution.
*
*/
import java.util.*;

/**
* Sudoku program
* 
* @author prashantpiyush piyush.5862@gmail.com
*/

public class Sudoku {
    
    /**
    * This method prints a 9x9 sudoku which is a
    * 2D matrix.
    * 
    * @param sudoku 2D matrix in which sudoku is stored
    */
    static void print(int[][] sudoku) {
        System.out.println();
        System.out.println("+-------+-------+-------+");
        
        for(int i=0; i<9; i++) {
            System.out.print("| ");
            for(int j=0; j<9; j++) {
                if(sudoku[i][j]==0)
                    // Print '-' when there is '0' as input
                    System.out.print("- ");
                else
                    System.out.print(sudoku[i][j]+" ");
                if(j==2 || j==5)
                    System.out.print("| ");
            }
            System.out.println("|");
            if(i==2 || i==5)
                System.out.println("+-------+-------+-------+");
        }
        
        System.out.println("+-------+-------+-------+");
        // Leaves some extra space
        for(int i=0; i<4; i++)
            System.out.println();
    }
    
    /**
    * This method canPut checks if we can place a number i
    * at postion (x,y) in matrix sudoku.
    *
    * @param x Row of matrix sudoku where i is to be placed
    * @param y Column of matrix sudoku where i is to placed
    * @param i The number to be checked if it can be placed 
    *          or not
    * @param sudoku Sudoku matrix
    * @return This method returns true if i can be placed and
    *         and false otherwise.
    *
    */
    static boolean canPut(int x, int y, int i, int[][] sudoku) {
        int a= (x/3)*3;
        int b= (y/3)*3;
        // Check for diagonals
        for(int j=0; j<3; j++) {
            for(int k=0; k<3; k++) {
                if(sudoku[j+a][k+b]==i)
                    return false;
            }
        }
        
        // Check for xth row
        for(int j=0; j<9; j++) {
            if(sudoku[x][j]==i)
                return false;
        }
        
        // Check for yth column
        for(int j=0; j<9; j++) {
            if(sudoku[j][y]==i)
                return false;
        }
        return true;
    }
    
    /**
    * This method makes use backtracking to solve the sudoku.
    * First this method puts a number in a cell of sudoku matrix
    * and then solves furthur, if anything goes wrong it retraces
    * its way back here and put something else and then solves again.
    *
    * @param x Row of the cell where this method is going to put a
    *          number
    * @param y Column of the cell where this method is going to put a
    *          number
    * @param sudoku The matrix sudoku which is being solved here
    * @return boolean This method returns true if the sudoku can be
    *          solved and false otherwise
    *
    */
    static boolean solve(int x, int y, int[][] sudoku) {
        // Move from first cell to last one, if nothing goes wrong in
        // between then you have solved the sudoku.
        if(y==9) {
            y=0;
            x++;
            if(x==9)
                return true;
        }
        // Skip if a number is already present
        if(sudoku[x][y]!=0)
            return solve(x, y+1, sudoku);
        
        // Check for every number 1-9
        for(int i=1; i<10; i++) {
            if(canPut(x,y,i,sudoku)==true) {
                // Place a number at (x,y)
                sudoku[x][y] = i;
                // Solve subproblem
                if(solve(x, y+1, sudoku)==true)
                    // If solution found the return true
                    return true;
            }
        }
        // If no solution found for any numner(1-9) then undo whatere 
        // changes were made i.e., remove the number from (x,y) and 
        // return false
        sudoku[x][y]=0;
        return false;
    }
    
    /**
    * This is the main method which takes the sudoku input as a 2D
    * matrix and then calss solve method to find its solution and 
    * then prints it.
    *
    * @param args Command line argument arrays
    * @return void This method returns nothing
    *
    */
    public static void main(String[] args) {        
        Scanner scan = new Scanner(System.in);
        // Input numbers in sudoku matrix and
        // input zero where nothing is filled in sudoku
        /* Sample input :
        
        int[][] sudoku = {{5,3,0,0,7,0,0,0,0},
                        {6,0,0,1,9,5,0,0,0},
                        {0,9,8,0,0,0,0,6,0},
                        {8,0,0,0,6,0,0,0,3},
                        {4,0,0,8,0,3,0,0,1},
                        {7,0,0,0,2,0,0,0,6},
                        {0,6,0,0,0,0,2,8,0},
                        {0,0,0,4,1,9,0,0,5},
                        {0,0,0,0,8,0,0,7,9}};
        */
        int[][] sudoku = new int[9][9];
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                sudoku[i][j] = scan.nextInt();
            }
        }
        
        print(sudoku);
        boolean ans = solve(0, 0, sudoku);
        if(ans) {
            print(sudoku);
        } else {
            System.out.println("Can't solve this sudoku.");
        }
    }
}

