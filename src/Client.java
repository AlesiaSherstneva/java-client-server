import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);

        System.out.println(clientSocket.getInputStream().read());

        clientSocket.close();
    }
}