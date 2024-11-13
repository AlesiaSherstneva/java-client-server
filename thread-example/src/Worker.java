public class Worker implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("First");
        }
    }
}