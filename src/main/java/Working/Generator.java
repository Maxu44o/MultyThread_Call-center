package Working;

import java.time.LocalTime;
import java.util.Queue;

public class Generator extends Thread {
    private final long NEWCALLTIMEOUT = 400;

    LocalTime time = null;
    private Queue<Call> calls;
    int callnumber = 0;

    public Generator(Queue<Call> calls) {
        this.calls = calls;
    }

    @Override
    public void run() {
        System.out.println("Звонки начали поступать в " + time.now());
        try {
            while (!interrupted()) {
                callnumber++;
                calls.add(new Call(time.now().toString(), callnumber));
                Thread.sleep(NEWCALLTIMEOUT);
            }
        } catch (InterruptedException e) {
        } finally {
            System.out.println("Прием звонков окончен в " + time.now() + ". Всего поступило " + callnumber + "звонков");
        }
    }

    ;
}
