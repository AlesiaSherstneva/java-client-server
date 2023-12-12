import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int client = 0;
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client accepted " + ++client);

            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
            writer.write("<h1>Java</h1>");
            writer.flush();

            writer.close();
            clientSocket.close();
        }
    }
}