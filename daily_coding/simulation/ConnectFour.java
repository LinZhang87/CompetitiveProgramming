package simulation;

import java.util.Scanner;

/**
 * @author lzhang
 * @since 7/3/19
 */

/*
This problem was asked by Salesforce.

Connect 4 is a game where opponents take turns dropping red or black discs into a 7 x 6 vertically suspended grid. The game ends either when one player
creates a line of four consecutive discs of their color (horizontally, vertically, or diagonally), or when there are no more spots left in the grid.

Design and implement Connect 4.
 */

public class ConnectFour {
    private final int RED = -1;
    private final int BLACK = 1;
    private boolean p1 = true;
    private int[][] grid;
    private int count;

    public ConnectFour() {
        this.grid = new int[6][7];
        this.count = 42;
    }

    public void play() {
        Scanner reader = new Scanner(System.in);
        while(count > 0) {
            System.out.println("Player " + (p1 ? 1 : 2) + " choose a cell(enter two coordinates separated by space):");
            int x = reader.nextInt();
            int y = reader.nextInt();
            int color = p1 ? RED : BLACK;
            while(x < 0 || x > 5 || y < 0 || y > 6 || grid[x][y] != 0 || x < 5 && grid[x + 1][y] == 0) {
                System.out.println("Player " + (p1 ? 1 : 2) + " choose a cell(enter two coordinates separated by space):");
                x = reader.nextInt();
                y = reader.nextInt();
                color = p1 ? RED : BLACK;
            }
            grid[x][y] = color;
            count--;
            if(connectedFour(x, y, color)) {
                System.out.println("Player " + (p1 ? 1 : 2) + " won!");
                break;
            }
            p1 = !p1;
        }
        if(count == 0) {
            System.out.println("Tie!");
        }
        reader.close();
    }

    public void displayGrid() {

    }

    public boolean connectedFour(int x, int y, int color) {
        //check vertically
        if(x <= 2 && grid[x + 1][y] == color && grid[x + 2][y] == color && grid[x + 3][y] == color) {
            return true;
        }
        //check horizontally
        int d = 1, yLeft = y - 1, yRight = y + 1;
        while(yLeft >= 0 && grid[x][yLeft] == color) {
            d++;
            yLeft--;
        }
        while(yRight < 7 && grid[x][yRight] == color) {
            d++;
            yRight++;
        }
        if(d >= 4) {
            return true;
        }
        //check diagonally
        d = 1; yLeft = y - 1;
        int xLeft = x - 1;
        while(xLeft >= 0 && yLeft >= 0 && grid[xLeft][yLeft] == color) {
            d++;
            xLeft--;
            yLeft--;
        }
        int xRight = x - 1; yRight = y + 1;
        while(xRight >= 0 && yRight < 7 && grid[xRight][yRight] == color) {
            d++;
            xRight--;
            yRight++;
        }
        return d >= 4;
    }
    public static void main(String[] args) {
        ConnectFour cf = new ConnectFour();
        cf.play();
    }
}
