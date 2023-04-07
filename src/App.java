import java.util.Scanner;

public class App {
    // Prints the grid layout with the current marks
    public static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %c %c %c |\n", grid[i][0], grid[i][1], grid[i][2]);
        }
        System.out.println("---------");
    }

    // Gets the next move from the player and returns the coordinates.
    public static int[] getMove(char[][] grid, char player) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the coordinates: ");
            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (grid[row][col] == 'X' || grid[row][col] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            grid[row][col] = player;
            return new int[] { row, col };
        }
    }

    // Checks if the player has won the game.
    public static boolean checkWin(char[][] grid, char player) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) {
                return true;
            }
            if (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player) {
                return true;
            }
        }
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
            return true;
        }
        if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {
            return true;
        }
        return false;
    }

    // Checks if the game is a draw.
    public static boolean checkDraw(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != 'X' && grid[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] grid = new char[3][3];
        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            grid[row][col] = ' ';
        }
        char currentPlayer = 'X';
        printGrid(grid);
        while (true) {
            getMove(grid, currentPlayer);
            printGrid(grid);
            if (checkWin(grid, currentPlayer)) {
                System.out.println(currentPlayer + " wins");
                break;
            }
            if (checkDraw(grid)) {
                System.out.println("Draw");
                break;
            }
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        }
        scanner.close();
    }
}

