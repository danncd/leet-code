class Solution {
      // Start by initializing a List<List<String>> array that later will be called by the function solveNQueens.
    public List<List<String>> strList = new ArrayList<>();

      // The isOk function checks three tests (Horizontal, Upper/Lower diagonal) to see if it's safe to place a queen.
    public boolean isOk(int[][] board, int row, int col, int size) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) return false;
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }
        for (int i = row, j = col; i < size && j>=0; i++, j--) {
            if (board[i][j] == 1) return false;
        }
        return true;
    }

      // The addStr creates a List to story every row converted to string. When all rows are stored in the List, the List is added to strList.
    public void addStr(int[][] board, int size) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    sb.append(".");
                } else {
                    sb.append("Q");
                }
            }
            strings.add(sb.toString());
        }
        strList.add(strings);
    }
  
      /* 
      The solve function starts by checking if the col is equal to size which means that a solution has been found.
      Then, initiate a for loop while checking a position is safe.
      If it's safe, a queen is placed and then the function solve is called again but this time, increasing the col by 1.
      */
    public boolean solve(int[][] board, int col, int size) {
        if (col == size) {
            addStr(board, size);
            return true;
        }
        for (int i = 0; i < size; i++) {
            if (isOk(board, i, col, size)) {
                board[i][col] = 1;
                solve(board, col + 1, size);
                board[i][col] = 0;
            }
        }
        return false;

    }

      // Initiate the board, call the solve function and then return the strList.
    public List<List<String>> solveNQueens(int n) {
        int[][] board = new int[n][n];
        solve(board, 0, n);
        return strList;
    }
}
