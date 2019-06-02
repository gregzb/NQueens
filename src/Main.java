public class Main {

    //int size = 12;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {

        double prevTime = 0;

        for (int i = 1; i <= 17; i++) {
            System.out.println();
            long startTime = System.nanoTime();

            int[][] board = new int[i][i];

            System.out.println(nQueens(board, i - 1) + " solutions for n = " + i);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) * Math.pow(10, -9);
            System.out.println("Took " + time + " seconds");
            if (prevTime != 0) {
                System.out.println("That took " + (time/prevTime) + "x longer than the previous time");
            }

            prevTime = time;
        }
    }

    public int nQueens(int[][] board, int row) {
        if (row < 0) {
            return 1;
        }

        int total = 0;
        for (int col = 0; col < board.length; col++) {
            if (board[row][col] == 0) {
                editQueen(board, row, col, 1);

                total += nQueens(board, row-1);

                editQueen(board, row, col, -1);
            }
        }
        return total;
    }

    public void editQueen(int[][] board, int row, int col, int add) {
        for (int iRow = 0; iRow < board.length; iRow++) {
            board[iRow][col] += add;
        } //goes down row
        for (int iCol = 0; iCol < board.length; iCol++) {
            board[row][iCol] += add;
        } //goes down col

        int minVal = Math.min(row, col);
        int otherMinVal = Math.min(row, board.length-col-1);

        //top left diagonal
        for (int iRow = row-minVal, iCol = col-minVal; iRow < board.length && iCol < board.length; iRow++, iCol++) {
            board[iRow][iCol] += add;
        }

        //top right diagonal
        for (int iRow = row-otherMinVal, iCol = col+otherMinVal; iRow < board.length && iCol >= 0; iRow++, iCol--) {
            board[iRow][iCol] += add;
        }
    }
}
