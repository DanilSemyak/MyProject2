import java.util.concurrent.Semaphore;

public class Connection {

    private static Connection connection = new Connection();
    private int connectionsCounter;
    private Semaphore semaphore = new Semaphore(10);

    private Connection() {

    }

    public static Connection getConnection() {
        return connection;
    }

    public void work() throws InterruptedException {
        semaphore.acquire();
        doWork();
        semaphore.release();
    }

    private void doWork() throws InterruptedException {
        synchronized (this) {
            connectionsCounter++;
            System.out.println(connectionsCounter);
        }
        Thread.sleep(3000);
        synchronized (this) {
            connectionsCounter--;
            System.out.println(connectionsCounter);
        }
    }
}
