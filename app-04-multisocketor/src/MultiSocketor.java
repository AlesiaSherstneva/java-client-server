public class MultiSocketor {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("""
                    Usage:
                    java Socketor server 8000 2 /
                    java Socketor client 127.0.0.1 8000 25 5""");
            return;
        }

        MultiSocketor socketor = new MultiSocketor();

        if (args[0].equals("server")) {
            if (args[2].matches(".+\\.class")) {
                args[2] = "*";
            }
            socketor.runServer(args[1], args[2], args[3]);
        } else if (args[0].equals("client")) {
            socketor.runClient(args[1], args[2], args[3], args[4]);
        } else {
            System.out.println("Wrong mode, enter server or client");
        }
    }

    private void runServer(String port, String threadsCount, String operation) {
        int threads = Integer.parseInt(threadsCount);
        MultiPhone phone = new MultiPhone(port);
        for (int j = 1; j < threads; j++) {
            new Thread(new ServerPhone(new MultiPhone(phone), operation)).start();
        }
    }

    private void runClient(String ip, String port, String a, String b) {
        MultiPhone phone = new MultiPhone(ip, port);

        phone.writeLine(a);
        phone.writeLine(b);
        String answer = phone.readLine();
        System.out.println(answer);

        phone.close();
    }
}