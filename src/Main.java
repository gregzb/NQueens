import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    int size = 12;

    public static void main(String[] args) {
        new Main().run();
    }

    public int[][] copyBoard(int[][] board) {
        int [][] temp = board.clone();
        for(int i = 0; i < board.length; i++)
            temp[i] = board[i].clone();
        return temp;
    }

    public void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int val : row) {
                System.out.print(val);
            }
            System.out.println();
            //System.out.println(Arrays.toString(row));
        }
    }

    public void run() {

        long startTime = System.nanoTime();

        for (int i = 0; i < 1; i++) {

            int[][] board = new int[size][size];
            //System.out.println(nQueens(board, size));
            System.out.println(nQueens(board, size - 1));
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) * Math.pow(10, -9);
        System.out.println("Took " + time + " seconds");
    }

    public int nQueens(int[][] board, int row) {
        if (row < 0) {
            //System.out.println("");
            //printBoard(board);
            return 1;
        }

        int total = 0;
        for (int col = 0; col < size; col++) {
            int[][] boardCopy = copyBoard(board);
//                System.out.println();
//                System.out.println("Remaining: " + remaining);
//                System.out.println("row: " + row + ", col: " + col);
//                printBoard(boardCopy);
            if (boardCopy[row][col] == 0) {

                for (int iRow = 0; iRow < size; iRow++) {
                    boardCopy[iRow][col] = 1;
                } //goes down row
                for (int iCol = 0; iCol < size; iCol++) {
                    boardCopy[row][iCol] = 1;
                } //goes down col

                int minVal = Math.min(row, col);
                int otherMinVal = Math.min(row, size-col-1);

                //top left diagonal
                for (int iRow = row-minVal, iCol = col-minVal; iRow < size && iCol < size; iRow++, iCol++) {
                    boardCopy[iRow][iCol] = 1;
                }

                //top right diagonal
                for (int iRow = row-otherMinVal, iCol = col+otherMinVal; iRow < size && iCol >= 0; iRow++, iCol--) {
                    boardCopy[iRow][iCol] = 1;
                }

                boardCopy[row][col] = 2;

                total += nQueens(boardCopy, row-1);
            }
        }
        return total;
    }
}
