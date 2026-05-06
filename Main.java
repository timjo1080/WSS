import java.util.Scanner;

import Player.Player;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WSS wss = new WSS();
        String answer = "yes";

        System.out.println("Starting WSS game...");
        while(answer.equals("yes"))
        {
            wss.startGame();
            System.out.print("Do you want to play again? (yes/no): ");
            answer = scanner.next();
        }
        System.out.println("\n\nThank you for playing!");
    }
}