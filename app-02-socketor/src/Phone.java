import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
            createStreams();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void accept() {
        try {
            client = server.accept();
            createStreams();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createStreams() {
        try {
            reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    client.getInputStream()));
            writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    client.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLine(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
    }
}