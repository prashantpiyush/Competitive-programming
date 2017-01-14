/**
* <h1>N Queen Problem</h1>
* Problem : We have to place N queens on a NxN chessboard
* so that no two queens attack each other.
* The output should be in the form of a binary matrix where
* 0 will represent an empty cell and 1 if there is a queen
* placed in that cell.
*
* @author prashantpiyush
*/
import java.io.*;

class Nqueens {
    static int[][] board;
    static int l;
    
    /**
    * This method will print the binary matrix, board,
    * when the problem can be solved.
    */
    static void print() {
        System.out.println("YES");
        for(int i=0; i<l; i++) {
            for(int j=0; j<l; j++)
                System.out.print(board[i][j]+" ");
            System.out.println();
        }
    }

    /**
    * This method checks that if the cell (x,y) where
    * we are going to place the next queen is under-
    * attack or not. 
    * Returns true if the cell is underattack and 
    * false if not.
    *
    * @param x Row of the cell.
    * @param y Column of the cell.
    * @return  True if the cell is under attack and fals
    *          false otherwise.
    */
    static boolean underAttack(int x, int y) {
        // First check the row of the cell, if there is
        // any queen return true
        for(int i=0; i<l; i++) {
            if(board[x][i] == 1)
                return true;
        }
        // Check if there is a queen in that column, if
        // yes return true
        for(int i=0; i<l; i++) {
            if(board[i][y] == 1)
                return true;
        }
        // Check for all othere cells which are in diagonal
        // alingment with the cell
        for(int i=0; i<l; i++) {
            for(int j=0; j<l; j++) {
                if((x+y)==(i+j) && board[i][j] ==1)
                    return true;
                if((x-y)==(i-j) && board[i][j] ==1)
                    return true;
            }
        }
        // Return false if no queen found
        return false;
    }

    /**
    * Check of every row and column to place a queen. If a
    * cell is found which is not under attack then place it
    * there and try to solve for fewer queens, and if the
    * solution is found then thats it, we have solved this
    * problem. Otherwise, move the queen to next position
    * and again solve for one less queens. Keep doing this
    * till you are either out of queens or the cells.
    * <p>
    * If number of queen is zero(n=0), which means that you
    * have placed all the queen, then return true.
    * <p>
    * And, if you are out of cells to place the queens, 
    * which means there aren't any safe cells for the queen 
    * to be placed, then return false, as this configuration 
    * can't be solved.
    *
    * @param n Number of queens left to place.
    * @return  True if the problem can be solved and false if not.
    */
    static boolean queens(int n) {
        if(n==0)
            return true;
        for(int i=0; i<l; i++) {
            for(int j=0; j<l; j++) {
                if(underAttack(i, j)==false) {
                    // If it is safe to place the queen 
                    // then place it
                    board[i][j] =1; 
                    // Solve the subproblem
                    // If solution found the problem solved
                    if(queens(n-1)==true)
                        return true;
                    else
                        // If no solution then remove the queen 
                        board[i][j]=0;
                }
            }
        }
        // If no solution found for any cell then return false
        return false;

    }

    /**
    * The only input we are going to take is the value of N.
    * If it is possible of place all the queens without any two
    * of them attacking each other then it will print "YES" and
    * the binary matrix, and "NO" if all the queens can't be
    * placed like that.
    * <p>
    * <b>Note:</b> the binary matrix this program will print will be 
    * one of the several configurations where all the queens can
    * be placed without attacking each other.
    *
    * @param args
    * @throws IOException
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        l = n;
        
        boolean ans = queens(n);
        
        if(ans == true)
            print();
        else
            System.out.println("NO");
    }
}