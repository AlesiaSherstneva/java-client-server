public class Main {
    public static void main(String[] args) {
        Runnable worker = () -> {
            for (int j = 1; j <= 100; j++) {
                System.out.println(j);
            }
        };
        for (int i = 0; i < 5; i++) {
            new Thread(worker).start();
        }
    }
}