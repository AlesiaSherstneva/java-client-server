public class Socketor {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("""
                    Usage:
                    java Socketor server 8000 /
                    java Socketor client 127.0.0.1 8000 25 5""");
            return;
        }

        Socketor socketor = new Socketor();

        if (args[0].equals("server")) {
            if (args[2].matches(".+\\.class")) {
                args[2] = "*";
            }
            socketor.runServer(args[1], args[2]);
        } else if (args[0].equals("client")) {
            socketor.runClient(args[1], args[2], args[3], args[4]);
        } else {
            System.out.println("Wrong mode, enter server or client");
        }
    }

    private void runServer(String port, String operation) {
        Phone phone = new Phone(port);
        System.out.printf("Started server with \"%s\" operation on %s port\n", operation, port);

        //noinspection InfiniteLoopStatement
        while (true) {
            phone.accept();

            String a = phone.readLine();
            String b = phone.readLine();
            int result = calculate(operation, a, b);
            String message = String.format("%s %s %s = %d\n", a, operation, b, result);
            phone.writeLine(message);
            System.out.printf(message);

            phone.close();
        }
    }

    private void runClient(String ip, String port, String a, String b) {
        Phone phone = new Phone(ip, port);

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
            default -> throw new IllegalArgumentException("Wrong operation, choose from \"+ - * /\"");
        };
    }
}