import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);

        byte[] bytes = new byte[256];
        clientSocket.getInputStream().read(bytes);
        System.out.println(new String(bytes));

        clientSocket.close();
    }
}