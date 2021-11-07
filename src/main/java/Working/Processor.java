package Working;

import java.util.Queue;

public class Processor implements Runnable {
private final long WAITINGPERIOD = 600;

private final long PROCESSINGTIMEFACTOR = 4000;
private final long CONVERSIONFACTOR = 1000;

    private Queue<Call> calls;

    public Processor(Queue<Call> calls) {
        this.calls = calls;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                long timeOut = (long) ((Math.random() * PROCESSINGTIMEFACTOR) + CONVERSIONFACTOR);
                Call call = calls.poll();
                if (call != null) {
                    System.out.println("Оператор" + Thread.currentThread().getName() + " оботал звонок " + call.toString() + " за примерно " + timeOut / CONVERSIONFACTOR + " сек");
                    Thread.sleep(timeOut);
                } else {
                    System.out.println("Оператор" + Thread.currentThread().getName() + " ожидает звонок");
                    Thread.sleep(WAITINGPERIOD);
                }
            }
        } catch (
                InterruptedException e) {
            System.out.println("Оператор" + Thread.currentThread().getName() + " завершил работу");
        }
    }
}
