import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("BusyWait")
public class BCClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);
        System.out.println("Bulls and cows game client started");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.printf("I receive a message: \"%s\"\n", reader.readLine());
        Thread.sleep(3000);

        List<String> numsVars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int n = 0; n < 10; n++) {
                        if (i != j && i != k && i != n && j != k && j != n && k != n) {
                            numsVars.add(String.format("%s%s%s%s", i, j, k, n));
                        }
                    }
                }
            }
        }

        Random random = new Random();
        String request;
        String[] response;
        while (true) {
            Thread.sleep(1000);
            request = numsVars.remove(random.nextInt(numsVars.size()));
            System.out.println(request);
            writer.write(request);
            writer.newLine();
            writer.flush();

            Thread.sleep(1000);
            response = reader.readLine().split(" ");
            int bulls = Integer.parseInt(response[0]), cows = Integer.parseInt(response[2]);

            if (bulls == 4) {
                System.out.println("I've won!!! WooHoo!!!");
                break;
            }

            String[] digits = request.split("");
            if (bulls + cows == 0) {
                removeNumsWithWrongPositions(numsVars, digits);
            } else if (bulls + cows == 4) {
                removeNumsWithWrongSetOfDigits(numsVars, digits);
                if (cows == 4) {
                    removeNumsWithWrongPositions(numsVars, digits);
                }
            } else if (bulls + cows > 0) {
                removeNumsWithWrongSingleDigits(numsVars, digits);
            }
        }

        reader.close();
        writer.close();
        clientSocket.close();
    }

    private static void removeNumsWithWrongSetOfDigits(List<String> numsVars, String[] digits) {
        numsVars.removeIf(num -> !(num.contains(digits[0]) && num.contains(digits[1])
                && num.contains(digits[2]) && num.contains(digits[3])));
    }

    private static void removeNumsWithWrongPositions(List<String> numsVars, String[] digits) {
        numsVars.removeIf(num -> num.substring(0, 1).equals(digits[0]) || num.substring(1, 2).equals(digits[1])
                || num.substring(2, 3).equals(digits[2]) || num.substring(3, 4).equals(digits[3]));
    }

    private static void removeNumsWithWrongSingleDigits(List<String> numsVars, String[] digits) {
        numsVars.removeIf(num -> !(num.contains(digits[0]) || num.contains(digits[1])
                || num.contains(digits[2]) || num.contains(digits[3])));
    }
}