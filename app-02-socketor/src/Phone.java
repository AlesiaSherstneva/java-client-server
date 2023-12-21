import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Phone {
    ServerSocket server;
    Socket client;
    BufferedReader reader;
    BufferedWriter writer;

    public Phone(String port) {
    }

    public Phone(String ip, String port) {
    }

    public void accept() {
    }

    public String readLine() {
        return null;
    }

    public void writeLine(String message) {
    }

    public void close() {
    }
}