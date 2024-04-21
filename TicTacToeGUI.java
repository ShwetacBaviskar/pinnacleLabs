import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private static final int BOARD_SIZE = 3;
    private JButton[][] button = new JButton[BOARD_SIZE][BOARD_SIZE];
    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private char currentPlayer1 = 'X';
    private boolean game = true;

    public TicTacToeGUI() {
        initializeBoard();
        initializeGUI();
    }

    // Initialize the game board with empty spaces
    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = ' ';
            }
        }
    }

    // Initialize the GUI
    private void initializeGUI() {
        // Set the window title
        setTitle("Tic-Tac-Toe");

        // Set the layout for the game board
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        // Create a grid of buttons
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                button[row][col] = createButton(row, col);
                add(button[row][col]);
            }
        }

        // Configure the window
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Create a button for the specified row and column
    private JButton createButton(int row, int col) {
        JButton button = new JButton(" ");
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        button.addActionListener(new ButtonClickListener(row, col));
        return button;
    }

    // Event listener for button clicks
    private class ButtonClickListener implements ActionListener {
        private final int row;
        private final int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            handleButtonClick(row, col);
        }
    }

    // Handle button clicks
    private void handleButtonClick(int row, int col) {
        if (game && board[row][col] == ' ') {
            // Update the board with the current player's move
            board[row][col] = currentPlayer1;
            button[row][col].setText(String.valueOf(currentPlayer1));

            // Check for a winner
            if (checkWinner(row, col)) {
                game= false;
                  JOptionPane.showMessageDialog(this, "🎉 Congratulations! Player " + currentPlayer1 + " wins! 🎉");
            } else if (isBoardFull()) {
                game = false;
                JOptionPane.showMessageDialog(this, "It's a draw! 🤝 Well played!");
            } else {
                // Switch players
                currentPlayer1 = (currentPlayer1 == 'X') ? 'O' : 'X';
            }
        }
    }

    // Check if the current player has won
    private boolean checkWinner(int row, int col) {
        return checkRow(row) || checkCol(col) || checkDiagonals();
    }

    // Check if the specified row is won by the current player
    private boolean checkRow(int row) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[row][col] != currentPlayer1) {
                return false;
            }
        }
        return true;
    }

    // Check if the specified column is won by the current player
    private boolean checkCol(int col) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][col] != currentPlayer1) {
                return false;
            }
        }
        return true;
    }

    // Check if either diagonal is won by the current player
    private boolean checkDiagonals() {
        // Check the main diagonal
        boolean mainDiagonalWin = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][i] != currentPlayer1) {
                mainDiagonalWin = false;
                break;
            }
        }

        // Check the counter diagonal
        boolean counterDiagonalWin = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][BOARD_SIZE - 1 - i] != currentPlayer1) {
                counterDiagonalWin = false;
                break;
            }
        }

        return mainDiagonalWin || counterDiagonalWin;
    }

    // Check if the board is full
    private boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Main method to run the application
    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
