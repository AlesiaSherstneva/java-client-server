import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BCServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Bulls and cows game server started");

        Socket clientSocket = serverSocket.accept();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        Random random = new Random();
        List<Integer> numsList = new ArrayList<>();
        while (numsList.size() < 4) {
            int num = random.nextInt(10);
            if (!numsList.contains(num)) {
                numsList.add(num);
            }
        }

        String numStr = String.format("%d%d%d%d", numsList.get(0), numsList.get(1), numsList.get(2), numsList.get(3));
        System.out.printf("My digits are: %s\nI want client to guess them\n", numStr);

        Thread.sleep(3000);
        writer.write("I thought 4 various digits from 0 to 9. Guess which ones?");
        writer.newLine();
        writer.flush();

        boolean wasGuessed = false;
        String request, response;

        while (!wasGuessed) {
            Thread.sleep(1000);
            response = reader.readLine();
            int bulls = 0, cows = 0;
            if (response.equals(numStr)) {
                bulls = 4;
                wasGuessed = true;
            } else {
                for (int i = 0; i < 4; i++) {
                    int num = Integer.parseInt(String.valueOf(response.charAt(i)));
                    if (numsList.contains(num)) {
                        if (numsList.indexOf(num) == i) {
                            bulls++;
                        } else {
                            cows++;
                        }
                    }
                }
            }

            Thread.sleep(1000);
            request = String.format("%d bulls, %d cows", bulls, cows);
            if (wasGuessed) {
                System.out.printf("Yes, you're right! There were digits: %s\n", numStr);
            } else {
                System.out.println(request);
            }
            writer.write(request);
            writer.newLine();
            writer.flush();
        }

        reader.close();
        writer.close();
        clientSocket.close();
    }
}