package Working;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        final long CALLGENERATIONPERIOD = 10000;

        Queue<Call> calls = new ConcurrentLinkedQueue<>();
        // Queue<Call> calls = new LinkedBlockingQueue<>();

        ThreadGroup operators = new ThreadGroup("Операторы");

        Generator generator = new Generator(calls);
        generator.start();

        new Thread(operators, new Processor(calls), " №1").start();
        new Thread(operators, new Processor(calls), " №2").start();
        new Thread(operators, new Processor(calls), " №3").start();
        new Thread(operators, new Processor(calls), " №4").start();
        new Thread(operators, new Processor(calls), " №5").start();

        Thread.sleep(CALLGENERATIONPERIOD);
        generator.interrupt();

        while (calls.peek() != null) {
        }
        System.out.println("Обработка звонков завершена");
        operators.interrupt();

    }
}
