import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int count = 0;
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client accepted " + ++count);

            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String request = reader.readLine();
            String response = "№" + count + ", your message length is " + request.length();
            writer.write(response);
            writer.flush();

            reader.close();
            writer.close();
            clientSocket.close();
        }
    }
}