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

    @SuppressWarnings("BusyWait")
    private void runServer(String port, String threadsCount, String operation) {
        int threads = Integer.parseInt(threadsCount);
        MultiPhone[] phone = new MultiPhone[threads];
        System.out.printf("Started server with \"%s\" operation on %s port\n", operation, port);

        while (true) {
            phone.accept();

            String a = phone.readLine();
            String b = phone.readLine();
            int result = calculate(operation, a, b);
            String message = String.format("%s %s %s = %d\n", a, operation, b, result);

            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                break;
            }

            phone.writeLine(message);
            System.out.printf(message);

            phone.close();
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

    private int calculate(String operation, String a, String b) {
        int intA = Integer.parseInt(a), intB = Integer.parseInt(b);
        return switch (operation) {
            case "+" -> intA + intB;
            case "-" -> intA - intB;
            case "*" -> intA * intB;
            case "/" -> intA / intB;
            default -> throw new IllegalArgumentException("Wrong operation, choose one of \"+ - * /\"");
        };
    }
}