import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiPhone {
    private ServerSocket server;
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;

    public MultiPhone(String port) {
        try {
            server = new ServerSocket(Integer.parseInt(port));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public MultiPhone(MultiPhone phoneServer) {
        server = null;
        this.client = phoneServer.accept();
        createStreams();
    }

    public MultiPhone(String ip, String port) {
        try {
            client = new Socket(ip, Integer.parseInt(port));
            createStreams();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Socket accept() {
        try {
            return server.accept();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void createStreams() {
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));
            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            client.getOutputStream()));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void writeLine(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void close() {
        try {
            reader.close();
            writer.close();
            client.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void closeServer() {
        try {
            server.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}