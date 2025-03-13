import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Minesweeper {

    int tiles = 70;
    int mines = 10;
    int rows = 8;
    int cols = 8;
    int width = cols * tiles;
    int height = rows * tiles;
    
    JFrame frame = new JFrame("Minesweeper");
    ArrayList<ArrayList<Character>> board = new ArrayList<>();
    
    Minesweeper() {
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        initializeBoard();
        placeMines();
        displayBoard();
    }

    void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add('0');
            }
            board.add(row);
        }
    }

    void placeMines() {
        for (int i = 0; i < mines; i++) {
            int x = (int) (Math.random() * rows);
            int y = (int) (Math.random() * cols);
            board.get(x).set(y, 'M');
        }
    }

    void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
    }
}