public class Main {
    public static void main(String[] args) {
        Worker first = new Worker();

        Thread thread1 = new Thread(first);
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (true) {
                System.out.println("Third");
            }
        });
        thread2.start();

        while (true) {
            System.out.println("Second");
        }
    }
}