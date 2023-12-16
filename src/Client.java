import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

@SuppressWarnings("BusyWait")
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);
        System.out.println("Client started");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        int lowestNum = 1, highestNum = 1000, middleNum = (highestNum - lowestNum) / 2;

        System.out.println(reader.readLine());

        Thread.sleep(3000);
        System.out.println(middleNum);
        writer.write(String.valueOf(middleNum));
        writer.newLine();
        writer.flush();


        while (true) {
            String response = reader.readLine();
            if (response.equals("less")) {
                highestNum = middleNum;
            } else if (response.equals("more")) {
                lowestNum = middleNum;
            } else {
                Thread.sleep(2000);
                System.out.println("I've won!!! WooHoo!!!");
                break;
            }

            Thread.sleep(3000);
            middleNum = (highestNum + lowestNum) / 2;
            System.out.println(middleNum);

            writer.write(String.valueOf(middleNum));
            writer.newLine();
            writer.flush();
        }

        reader.close();
        writer.close();
        clientSocket.close();
    }
}