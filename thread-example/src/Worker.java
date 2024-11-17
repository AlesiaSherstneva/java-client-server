public class Worker implements Runnable {
    int count = 0;

    @Override
    public void run() {
        for (int j = 1; j < 100; j++) {
            count++;
            System.out.printf("%d - %d\n", j, count);
        }
    }
}