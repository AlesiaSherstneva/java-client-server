import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server started");

        Socket clientSocket = serverSocket.accept();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        int serverNum = new Random().nextInt(1000) + 1;
        System.out.println("My number is: " + serverNum);
        writer.write("I thought of a number from 1 to 1000. Guess which one?");
        writer.newLine();
        writer.flush();
        boolean wasGuessed = false;

        while (!wasGuessed) {
            Thread.sleep(3000);
            String response = reader.readLine();
            String request;
            int tempNum = Integer.parseInt(response);
            Thread.sleep(3000);
            if (tempNum > serverNum) {
                request = "less";
            } else if (tempNum < serverNum) {
                request = "more";
            } else {
                request = "Yes, you're right! It was the number: " + serverNum;
                wasGuessed = true;
            }
            writer.write(request);
            writer.newLine();
            writer.flush();
            System.out.println(request);
        }

        reader.close();
        writer.close();
        clientSocket.close();
    }
}