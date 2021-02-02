package tictactoe;
import java.util.Scanner;

public class Main {
    private static char[][] charStr = new char[3][3];
    private static int xOrdinate = 0;
    private static int yOrdinate = 0;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // write your code here
        String str = "";
        gameGrid(str);
        startGame();
    }
    public static void startGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                charStr[i][j] = ' ';

            }
        }
        boolean check;
        int temp = 0;
        int kemp = 1;
        String analyze = gridAnalyzeModified();
        while (temp < 9 && analyze == "Game not finished") {
            temp++;
            do {
                System.out.print("Enter the coordinates: ");
                String x1 = scanner.next();
                String x2 = scanner.next();
                if (x1.matches("-?\\d+(\\.\\d+)?") && x2.matches("-?\\d+(\\.\\d+)?")) {
                    xOrdinate = Integer.valueOf(x1);
                    yOrdinate = Integer.valueOf(x2);
                    if (xOrdinate < 1 || xOrdinate > 3 || yOrdinate < 1 || yOrdinate > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        check = false;
                    } else {
                        if (kemp % 2 != 0) {
                            check = gridCheckModified('X');

                        } else {
                            check = gridCheckModified('O');

                        }

                        if (!check) {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    }
                } else {
                    System.out.println("You should enter numbers!");
                    check = false;
                }



            } while (!check);
            kemp++;
            analyze = gridAnalyzeModified();

        }
        System.out.println(analyze);
    }
    public static void gameGrid(String str) {
         if (str.isEmpty()) {
             str = "         ";
         }
         System.out.println("---------");
         int k = 0;
         for (int i = 0; i < 3; i++) {
             System.out.print("| ");
             for (int j = 0; j < 3; j++) {
                 System.out.print(str.charAt(k) + " ");
                 //System.out.print(" " + " ");
                 k++;
             }
             System.out.println("|");
         }
         System.out.println("---------");
     }

    public static boolean gridCheckModified(char input) {
        if (charStr[xOrdinate - 1][yOrdinate - 1] != ' ') {
            return false;
        } else {
            charStr[xOrdinate - 1][yOrdinate - 1] = input;
            String s = "";
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < charStr[i].length; j++) {
                    s += charStr[i][j];
                }
            }
            gameGrid(s);
            return true;
        }

    }
    public static int gridElements() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (charStr[i][j] != ' ') {
                    count++;
                }
            }
        }
//        System.out.println(count);
        return count;
    }
    public static String gridAnalyzeModified() {
        int count = gridElements();
        boolean check1 = false;
        boolean check2 = false;
        int xNumber = 0;
        int oNumber = 0;
        int diff;
        int xDiagonal = 0;
        int oDiagonal = 0;
        int xDiagonalL = 0;
        int oDiagonalL = 0;
        for (int i = 0; i < 3; i++) {
            int xRow = 0;
            int oRow = 0;
            int xCol = 0;
            int oCol = 0;

            for (int j = 0; j < 3; j++) {
                if (charStr[i][j] == 'X') {
                    xRow++;
                    xNumber++;
                } else if (charStr[i][j] == 'O') {
                    oRow++;
                    oNumber++;
                }
                if (charStr[j][i] == 'X') {
                    xCol++;
                } else if (charStr[j][i] == 'O') {
                    oCol++;
                }
                if (i == j && charStr[i][j] == 'X') {
                    xDiagonal++;
                } else if (i == j && charStr[i][j] == 'O') {
                    oDiagonal++;
                }
                if (Math.abs(i + j - 2) == 0 && charStr[i][j] == 'X') {
                    xDiagonalL++;
                } else if (Math.abs(i + j - 2) == 0 && charStr[i][j] == 'O') {
                    oDiagonalL++;
                }

            }

            if (xRow == 3) {
                check1 = true;
            } else if (oRow == 3) {
                check2 = true;
            } else if (xCol == 3) {
                check1 = true;
            } else if (oCol == 3) {
                check2 = true;
            } else if (xDiagonal == 3) {
                check1 = true;
            } else if (oDiagonal == 3) {
                check2 = true;
            } else if (xDiagonalL == 3) {
                check1 = true;
            } else if (oDiagonalL == 3) {
                check2 = true;
            }
        }
        diff = Math.abs(xNumber - oNumber);
        if (check1 && !check2) {
            return  "X wins";
        } else if (check2 && !check1) {
            return "O wins";
        } else if (!check1 && !check2 && count == 9) {
            return "Draw";
        } else if (!check1 && !check2 && count != 9 && diff <= 1) {
            return "Game not finished";
        } else if (check1 && check2 || diff > 1) {
            return "Impossible";
        }

        return null;
    }


}