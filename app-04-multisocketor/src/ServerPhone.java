public class ServerPhone implements Runnable {
    private final MultiPhone phone;
    private final String operation;

    public ServerPhone(MultiPhone phone, String operation) {
        this.phone = phone;
        this.operation = operation;
    }

    @Override
    @SuppressWarnings("BusyWait")
    public void run() {
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