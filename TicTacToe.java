import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    JLabel statusLabel;
    JButton restartButton;

    String currentPlayer = "X";
    boolean gameOver = false;

    TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setLayout(new BorderLayout());

        // Top panel (status)
        statusLabel = new JLabel("Player X Turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(statusLabel, BorderLayout.NORTH);

        // Center panel (grid)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 5, 5));
        panel.setBackground(Color.BLACK);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);

        // Bottom panel (restart)
        restartButton = new JButton("Restart Game");
        restartButton.setFont(new Font("Arial", Font.BOLD, 16));
        restartButton.addActionListener(e -> resetGame());
        add(restartButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("") || gameOver) {
            return;
        }

        clicked.setText(currentPlayer);

        if (checkWinner()) {
            statusLabel.setText("Player " + currentPlayer + " Wins!");
            gameOver = true;
            return;
        }

        if (isDraw()) {
            statusLabel.setText("Game Draw!");
            gameOver = true;
            return;
        }

        // Switch player
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        statusLabel.setText("Player " + currentPlayer + " Turn");
    }

    boolean checkWinner() {
        String[][] b = new String[3][3];

        for (int i = 0; i < 9; i++) {
            b[i / 3][i % 3] = buttons[i].getText();
        }

        for (int i = 0; i < 3; i++) {
            if (!b[i][0].equals("") && b[i][0].equals(b[i][1]) && b[i][1].equals(b[i][2]))
                return true;

            if (!b[0][i].equals("") && b[0][i].equals(b[1][i]) && b[1][i].equals(b[2][i]))
                return true;
        }

        if (!b[0][0].equals("") && b[0][0].equals(b[1][1]) && b[1][1].equals(b[2][2]))
            return true;

        if (!b[0][2].equals("") && b[0][2].equals(b[1][1]) && b[1][1].equals(b[2][0]))
            return true;

        return false;
    }

    boolean isDraw() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    void resetGame() {
        for (JButton b : buttons) {
            b.setText("");
        }
        currentPlayer = "X";
        gameOver = false;
        statusLabel.setText("Player X Turn");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
