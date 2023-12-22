import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Phone {
    ServerSocket server;
    Socket client;
    BufferedReader reader;
    BufferedWriter writer;

    public Phone(String port) {
        try {
            server = new ServerSocket(Integer.parseInt(port));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Phone(String ip, String port) {
        try {
            client = new Socket(ip, Integer.parseInt(port));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void accept() {
        try {
            client = server.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine() {
        return null;
    }

    public void writeLine(String message) {
    }

    public void close() {
    }
}