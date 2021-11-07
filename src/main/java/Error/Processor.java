package Error;

import java.util.concurrent.LinkedBlockingQueue;

public class Processor implements Runnable {
    private LinkedBlockingQueue<Call> calls;

    public Processor(LinkedBlockingQueue<Call> calls) {
        this.calls = calls;
    }

    @Override
    public void run() {
        try {
            while (Thread.interrupted()) {
                long timeOut = (long) ((Math.random() * 4000) + 1000);
                Thread.sleep(200);
                if (calls.peek() == null) {
                    System.out.println("Оператор" + Thread.currentThread().getName() + " ожидает звонок");
                } else {
                    Call call = calls.poll();
                    System.out.println("Оператор" + Thread.currentThread().getName() + " оботал звонок " + call.toString() + " за примерно " + timeOut / 1000 + " сек");

                }
            }
        } catch (InterruptedException e) {
        }
    }
}