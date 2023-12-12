import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("DanglingJavadoc")
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket clientSocket = serverSocket.accept();

        /** send symbol '@' */
        // clientSocket.getOutputStream().write(64);

        /** send numbers from 0 to 9 */
        // for (int j = 48; j < 58; j++) {
        //    clientSocket.getOutputStream().write(j);
        // }

        /** send a word in html */
        // clientSocket.getOutputStream().write("<h2>Hello</h2>".getBytes());

        /** send a message with OutputStreamWriter */
        OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
        writer.write("<h1>Java</h1>");
        writer.flush();

        writer.close();
        clientSocket.close();
        serverSocket.close();
    }
}