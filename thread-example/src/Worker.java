public class Worker implements Runnable {
    int count = 0;

    @Override
    public void run() {
        for (int j = 1; j <= 100; j++) {
            next();
            System.out.printf("%d - %d\n", j, count);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    synchronized void next() {
        count++;
    }
}