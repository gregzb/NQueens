import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static ArrayList<Location> directions;
    int size = 8;

    public static void main(String[] args) {
        new Main().run();
    }

    public int[][] copyBoard(int[][] board) {
        int [][] temp = new int[board.length][];
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

        int[][] board = new int[size][size];
        //System.out.println(nQueens(board, size));
        System.out.println(nQueens(board, size-1));
    }

    public int nQueens(int[][] board, int row) {
        if (row < 0) {
            System.out.println("");
            printBoard(board);
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

    public int nQueens2(int[][] board, int remaining) {
        if (remaining == 0) {
            //System.out.println("found one");
            System.out.println();
            printBoard(board);
            return 1;
        }
        //int yeet = true;
        int total = 0;
        for (int row = 0; row < size; row++) {
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

                    total += nQueens(boardCopy, remaining-1);
                }
            }
        }
        return total;
    }

    static class Location {
        public final int x, y;
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Location(Location l) {
            this.x = l.x;
            this.y = l.y;
        }

        public Location add(Location l) {
            return new Location(x+l.x, y+l.y);
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        public boolean equals(Object o) {
            if (!(o instanceof Location)) {
                return false;
            }
            Location l = (Location) o;
            return x == l.x && y == l.y;
        }
    }
}
