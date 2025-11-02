// Constraint checking module for pruning invalid moves
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false; // column check
            if (board[row][i] == c) return false; // row check
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
                return false; // subgrid check
        }
        return true;
    }
}
