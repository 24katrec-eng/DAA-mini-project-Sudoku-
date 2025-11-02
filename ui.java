import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuUI extends JFrame {
    private JTextField[][] textFields = new JTextField[9][9];
    private JButton solveButton;
    private Solution solver = new Solution();

    public SudokuUI() {
        setTitle("Automated Puzzle Solver - Sudoku");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        Font font = new Font("Arial", Font.BOLD, 20);

        // Initialize text fields
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = new JTextField();
                textFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                textFields[i][j].setFont(font);
                gridPanel.add(textFields[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        // Add button
        solveButton = new JButton("Solve Sudoku");
        add(solveButton, BorderLayout.SOUTH);

        // Predefined board
        int[][] initialBoard = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        // Display initial board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (initialBoard[i][j] != 0)
                    textFields[i][j].setText(String.valueOf(initialBoard[i][j]));
            }
        }

        // Button listener
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char[][] board = new char[9][9];

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        String text = textFields[i][j].getText();
                        board[i][j] = text.isEmpty() ? '.' : text.charAt(0);
                    }
                }

                if (solver.solveSudoku(board)) {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            textFields[i][j].setText(String.valueOf(board[i][j]));
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Sudoku Solved Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No Solution Found!");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new SudokuUI();
    }
}
