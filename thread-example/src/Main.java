public class Main {
    public static void main(String[] args) {
        Worker worker = new Worker();

        for (int i = 0; i < 5; i++) {
            new Thread(worker).start();
        }
    }
}