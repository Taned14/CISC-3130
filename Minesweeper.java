import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Minesweeper {

    int tiles = 70;
    int mines = 10;
    int rows = 8;
    int cols = 8;
    int width = cols * tiles;
    int height = rows * tiles;
    
    JFrame frame = new JFrame("Minesweeper");
    ArrayList<ArrayList<Character>> board = new ArrayList<>(); // Grid storage
    ArrayList<Point> mineLocations = new ArrayList<>(); // Mine pos
    
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
                row.add('0'); // Empty tile
            }
            board.add(row);
        }
    }

    void placeMines() {
        Random random = new Random();
        int count = 0;
        while (count < mines) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            if (board.get(x).get(y) != '*') {
                board.get(x).set(y, '*');
                mineLocations.add(new Point(x, y));
                count++;
            }
        }
    }

    void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board.get(i).get(j) + " ");
            }
            System.out.println();
        }

        for (Point p : mineLocations) {
            System.out.println(p.x + " " + p.y);
        }
    }

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
    }
}