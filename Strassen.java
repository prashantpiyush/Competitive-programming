/**
* Strassen.java
*
* This program multiplies two matrices by Strassen's matrix
* multiplication algorithm.
* @see https://en.wikipedia.org/wiki/Strassen_algorithm
*
* @author prashantpiyush
*/
import java.io.*;
import java.util.*;

public class Strassen{
    
    public static void printMatrix(int[][] matrix) {
        /**
        * This matrix prints the input 2D matrix
        *
        * @param matrix
        */
        for(int[] x : matrix) {
            for(int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }
    
    static int[][] multiply(int[][] first, int[][] second) {
        /**
        * This method multiplies two input matrices by strassen's
        * algorithm.
        *
        * @param first First of the two matrices to be multiplied
        * @param second Second of the two matrices to be multiplied
        */
        
        // If number of columns of first matrix is not equal to number
        // of rows to second matrix return Invalid input
        if(first[0].length != second.length) {
            System.out.println("Invalid Dimensions : multiply method");
            return (new int[0][0]);
        }        
        
        // if not square matrices then add extra rows/columns to make
        // square, and for the final ans these extra rows/columns will
        // be removed
        if(first.length != first[0].length || second.length != second[0].length) {
            int r1 = first.length;
            int c1 = first[0].length;
            int r2 = c1;
            int c2 = second[0].length;
            
            //find maximum of three r1, c1=r2, c2
            int n =0;
            if(r1 >= c1 && r1 >= c2) {
                n = r1;
            } else if(c1 >= r1 && c1 >= c2) {
                n = c1;
            } else if(c2 >= r1 && c2 >= c1) {
                n = c2;
            }
            
            int[][] A = new int[n][n];
            int[][] B = new int[n][n];
            
            fill(A, first, 0, 0);
            fill(B, second, 0, 0);
            
            // now multiply these new square matrices and extract the ans from them
            int[][] R = multiply(A, B);
            int[][] out = new int[r1][c2];
            
            for(int i=0; i<r1; i++) {
                for(int j=0; j<c2; j++) {
                    out[i][j] = R[i][j];
                }
            }
            return out;
        }
        
        // for square matrices
        // dimension of input matrices
        int n = first.length;
        int[][] out = new int[n][n];
        
        if(n%2 != 0 && n !=1) {
            
            int[][] A = new int[n+1][n+1];
            int[][] B = new int[n+1][n+1];
            int[][] C = new int[n+1][n+1];
            
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    
                    A[i][j] = first[i][j];
                    B[i][j] = second[i][j];
                }
            }
            C = multiply(A, B);
            for(int i=0; i<n; i++)
                for(int j=0; j<n; j++)
                    out[i][j] = C[i][j];
            return out;
        }
        
        if(n==1) {
            out[0][0] = first[0][0] * second[0][0];
            return out;
        }
        
        
        // now multiplying the given matrices by STRASSEN's algorithm
        // which gives product of two matrices divided in four parts
        // and these parts can be calculated by his seven special matrices.
        // these special matrices are a combination of four parts of the 
        // given matrices
        /* how to divide a matrix in four parts : 
           [      ]     [ I  | II ]
           [      ]  _  [____|____]
           [      ]  =  [    |    ]
           [      ]     [III | IV ]

           four parts made of half*half area
        */

        // Four parts of the first matrix
        int[][] A = extract(first, 0, 0);
        int[][] B = extract(first, 0, n/2);
        int[][] C = extract(first, n/2, 0);
        int[][] D = extract(first, n/2, n/2);

        // Parts of the second matrix.
        int[][] E = extract(second, 0, 0);
        int[][] F = extract(second, 0, n/2);
        int[][] G = extract(second, n/2, 0);
        int[][] H = extract(second, n/2, n/2);

        // Seven special matrices required for the strassen's algorithm
        // which are a combination of the above 8 matrices.
        int [][] P1 = multiply(A, sub(F, H));
        int [][] P2 = multiply(add(A, B), H);
        int [][] P3 = multiply(add(C, D), E);
        int [][] P4 = multiply(D, sub(G, E));
        int [][] P5 = multiply(add(A, D), add(E, H));
        int [][] P6 = multiply(sub(B, D), add(G, H));
        int [][] P7 = multiply(sub(A, C), add(E, F));

        // Joing the seven strassen's matrices to form part of final
        // output matrix
        int[][] one = sub(add(add(P5, P4), P6), P2);
        int[][] two = add(P1, P2);
        int[][] three = add(P3, P4);
        int[][] four = sub(add(P1, P5), add(P3, P7));
        
        // Fill these four final parts in to form a final output matrix
        fill(out, one, 0, 0);
        fill(out, two, 0, n/2);
        fill(out, three, n/2, 0);
        fill(out, four, n/2, n/2);

        return out;
    }

    static int[][] add(int[][] first, int[][] second) {
        /**
        * This method performs addition of input matrices and
        * return their sum or nothing if input matrices can't be
        * added.
        */
        if(first.length != second.length || first[0].length != second[0].length) {
            System.out.println("Invalid Dimension : add method");
            return (new int[0][0]);
        }
        
        int n =first.length;
        
        int[][] out = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                out[i][j] = first[i][j] + second[i][j];
            }
        }
        return out;
    }

    static int[][] sub(int[][] first, int[][] second) {
        /**
        * This method performs subtraction of input matrices and
        * return their sum or nothing if input matrices can't be
        * subtracted.
        */
        if(first.length != second.length || first[0].length != second[0].length) {
            System.out.println("Invalid Dimension : sub method");
            return (new int[0][0]);
        }
        
        int n =first.length;
        
        int[][] out = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                out[i][j] = first[i][j] - second[i][j];
            }
        }
        return out;
    }


    static int[][] extract(int[][] matrix, int a, int b) {
        /*
        * Method to extract 1/4th portion of matrix to make parts as 
        * specified in multiplty(param list) method.
        *
        * @param matrix New matrix will be made from this matrix
        * which portions is to be extracted is defined by 
        * @param a and @param b
        *
        *   0  , 0   = first quadrant
        *   0  , n/2 = second
        *   n/2, 0   = third
        *   n/2, n/2 = fourth
        */
        int n = matrix.length;
        n /= 2;

        int[][] out = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                out[i][j] = matrix[a+i][b+j];
            }
        }
        return out;
    }

    static void fill(int[][] matrix, int[][] input, int a, int b) {
        /*
        * Method to fill a given matrix with the contents of a input 
        * matrix at position specified with a and b.
        *
        * @param matrix
        * @param input
        * @param a
        * @param b
        */
        int r = input.length;
        int c = input[0].length;
        
        for(int i=0; i< r; i++) {
            for(int j=0; j<c; j++) {
                matrix[a+i][b+j] = input[i][j];
            }
        }
    }
    
    public static void main(String[] args) {
        /**
        * This is the main method. It includes code for input of
        * matrices to be multiplied and then call multiply method
        * to multiply these matrices. It finally calls printMatrix()
        * to print the output.
        */
        
        // Code for taking two matrices as input is to be added here.
        // Two sample matrices A and B are given.
        
        int[][] A = {{1, 0, 1, 1},
                     {1, 0, 0, 0},
                     {0, 1, 1, 0}};
                     //{1, 1, 0, 1}};
        
        int[][] B = {{1, 1, 1},
                     {0, 1, 1},
                     {1, 0, 0},
                     {0, 0, 1}};
        
        int[][] C = multiply(A, B);
        
        printMatrix(C);
    }
}