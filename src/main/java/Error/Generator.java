package Error;

import java.time.LocalTime;
import java.util.concurrent.LinkedBlockingQueue;

public class Generator extends Thread {

    LocalTime time = null;
    private LinkedBlockingQueue<Call> calls;
    int callnumber = 0;

    public Generator(LinkedBlockingQueue<Call> calls) {
        this.calls = calls;
    }

    @Override
    public void run() {
        System.out.println("Звонки начали поступать в " + time.now() );
        try {
            while (!interrupted()) {
                callnumber++;
                calls.add(new Call(time.now().toString(), callnumber));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
        } finally {
            System.out.println("Прием звонков окончен в " +  time.now() + ". Всего поступило " + callnumber + "звонков");
        }
    }

    ;
}
