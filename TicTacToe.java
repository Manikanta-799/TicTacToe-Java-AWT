import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener {

    Button[] buttons = new Button[9];
    String currentPlayer = "X";

    TicTacToe() {
        setTitle("Tic Tac Toe");

        setLayout(new GridLayout(3, 3));

        // Create buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setSize(300, 300);
        setVisible(true);

        // Close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Button clicked = (Button) e.getSource();

        // If already filled, ignore
        if (!clicked.getLabel().equals("")) {
            return;
        }

        // Set X or O
        clicked.setLabel(currentPlayer);

        // Check winner
        if (checkWinner()) {
            setTitle("Player " + currentPlayer + " Wins!");
            disableButtons();
            return;
        }

        // Switch player
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    boolean checkWinner() {
        String[][] b = new String[3][3];

        for (int i = 0; i < 9; i++) {
            b[i / 3][i % 3] = buttons[i].getLabel();
        }

        // Rows, Columns, Diagonals
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

    void disableButtons() {
        for (Button b : buttons) {
            b.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}