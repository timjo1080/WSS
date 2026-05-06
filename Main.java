import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WSS wss = new WSS();
        String answer = "yes";

        while(answer.equals("yes"))
        {
            wss.startGame();
            System.out.print("Do you want to play again? (yes/no): ");
            answer = scanner.next();
        }
        System.out.println("\n\nThank you for playing!");
    }
}