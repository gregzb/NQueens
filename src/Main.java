public class Main {

    int size = 13;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {

        long startTime = System.nanoTime();

        for (int i = 0; i < 1; i++) {

            boolean[] cols = new boolean[size];
            for (int j = 0; j < size; j++) {
                cols[j] = true;
            }

            int[][] board = new int[size][size];

            System.out.println(nQueens(board, size - 1, cols));
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) * Math.pow(10, -9);
        System.out.println("Took " + time + " seconds");
    }

    public int nQueens(int[][] board, int row, boolean[] cols) {
        if (row < 0) {
            return 1;
        }

        int total = 0;
        for (int col = 0; col < cols.length; col++) {
            if (!cols[col]) continue;
            if (board[row][col] == 0) {
                editQueen(board, row, col, 1);
                cols[col] = false;

                total += nQueens(board, row-1, cols);

                editQueen(board, row, col, -1);
                cols[col] = true;
            }
        }
        return total;
    }

    public void editQueen(int[][] board, int row, int col, int add) {
        for (int iRow = 0; iRow < size; iRow++) {
            board[iRow][col] += add;
        } //goes down row
        for (int iCol = 0; iCol < size; iCol++) {
            board[row][iCol] += add;
        } //goes down col

        int minVal = Math.min(row, col);
        int otherMinVal = Math.min(row, size-col-1);

        //top left diagonal
        for (int iRow = row-minVal, iCol = col-minVal; iRow < size && iCol < size; iRow++, iCol++) {
            board[iRow][iCol] += add;
        }

        //top right diagonal
        for (int iRow = row-otherMinVal, iCol = col+otherMinVal; iRow < size && iCol >= 0; iRow++, iCol--) {
            board[iRow][iCol] += add;
        }
    }
}
