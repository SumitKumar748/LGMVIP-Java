import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//all the necessary package were imported above, which is important for the game

public class TicTacToeGame extends JFrame {
    public JButton[][] buttons;
    public char currentPlayer = 'X';

    //board appearance, height & width designed below
    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[3][3];
        initComponents();
        layoutComponents();
    }

    //board is initialized below
    public void initComponents() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        if (button.getText().equals("")) {
                            button.setText(String.valueOf(currentPlayer));
                            button.setEnabled(false);
                            if (checkForWin()) {
                                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                                resetGame();
                            } else if (isBoardFull()) {
                                JOptionPane.showMessageDialog(null, "It's a draw!");
                                resetGame();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                            }
                        }
                    }
                });
            }
        }
    }

    //board layout is designed below
    public void layoutComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                panel.add(buttons[row][col]);
            }
        }
        add(panel);
    }
    //below is the algorithm for winning and draw condition.
    public boolean checkForWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[1][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeGame().setVisible(true);
            }
        });
    }
}
