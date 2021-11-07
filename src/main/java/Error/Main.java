package Error;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //   Queue<Call> calls = new ConcurrentLinkedQueue<>();
        LinkedBlockingQueue<Call> calls = new LinkedBlockingQueue<>();

        Generator generator = new Generator(calls);
        generator.start();

        Thread.sleep(2000);

        Thread t1 = new Thread(new Processor(calls), " №1");
        Thread t2 = new Thread(new Processor(calls), " №2");
        Thread t3 = new Thread(new Processor(calls), " №3");
        Thread t4 = new Thread(new Processor(calls), " №4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Thread.sleep(10000);
        generator.interrupt();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        //      generator.join();

        System.out.println("Обработка звонков завершена");

        while (calls.isEmpty()) {
            Call call = calls.remove();
            System.out.println(call.toString());
        }
    }
}
