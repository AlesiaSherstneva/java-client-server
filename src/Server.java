import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("DanglingJavadoc")
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket clientSocket = serverSocket.accept();

        /** send symbol '@' to client*/
        // clientSocket.getOutputStream().write(64);

        clientSocket.close();
        serverSocket.close();
    }
}