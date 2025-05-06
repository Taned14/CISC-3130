import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    HashSet<Point> revealedTiles = new HashSet<>(); // Visited tiles
    HashMap<Point, Integer> mineCount = new HashMap<>(); // Mine count around tile
    
    Minesweeper() {
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
    
        initializeBoard();
        placeMines();
        calculateAdjacentMines();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setFocusableWindowState(true);
        frame.requestFocus();
        frame.setAlwaysOnTop(true);
        displayBoard();

        revealTiles(2, 3);
        revealTiles(4, 5);
        revealTiles(6, 7);
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

    void calculateAdjacentMines() {
        for (Point mine : mineLocations) {
            int x = mine.x;
            int y = mine.y;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue; // Skip the mine itself
                    int newX = x + i;
                    int newY = y + j;
                    if (isValid(newX, newY)) {
                        Point adjacentTile = new Point(newX, newY);
                        mineCount.put(adjacentTile, mineCount.getOrDefault(adjacentTile, 0) + 1);
                    }
                }
            }
        }
    }

    boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
    int countAdjacentMines(int x, int y) {
        return mineCount.getOrDefault(new Point(x, y), 0);
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
        System.out.println("Current Board:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board.get(i).get(j) + " ");
            }
            System.out.println();
        }
        
        System.out.println("Mine Locations:");
         // Display mine locations
         // Use a loop to iterate through the mineLocations list
         // Print the x and y coordinates of each mine location
         // Use the Point class to access the x and y coordinates
         // Format the output for better readability
         // Use System.out.println() to print each mine location
        for (Point p : mineLocations) {
            System.out.println(p.x + " " + p.y);
        }

        System.out.println("Adjacent Mine Counts:");
        // Display adjacent mine counts
        // Use a loop to iterate through the mineCount map
        // Print the coordinates and mine count for each tile
        // Use the Point class to access the coordinates
        // Format the output for better readability
        // Use System.out.println() to print each tile and its mine count
        for (Point p : mineCount.keySet()) {
            System.out.println(p.x + " " + p.y + ": " + mineCount.get(p));
        }
    }

    void revealTiles(int x, int y) {
        if (revealedTiles.contains(new Point(x, y))) {
            System.out.println("Already revealed");
            return;
        }
        revealedTiles.add(new Point(x, y));
        System.out.println("Revealed tile at: " + x + " " + y);
    
        if (board.get(x).get(y) == '*') {
            System.out.println("Game Over! You hit a mine.");
            return;
        }
    
        int mineCount = countAdjacentMines(x, y);
        board.get(x).set(y, (char) (mineCount + '0'));
        displayBoard();
    }
    
    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
    }
} 

