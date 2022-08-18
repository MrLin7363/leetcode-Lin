 

package hw.差分;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/16
 **/
public class LogSystem {

    private Deque<Log> logList;

    public void setLogList(Deque<Log> logList) {
        this.logList = logList;
    }

    public Deque<Log> getLogList() {
        return logList;
    }

    private class Log{
        private int id;

        private int timeStamp;

        public int getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(int timeStamp) {
            this.timeStamp = timeStamp;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public LogSystem() {
        this.setLogList(new ArrayDeque<>());
    }

    public void add(int id, int timeStamp) {
        Log log = new Log();
        log.setId(id);
        log.setTimeStamp(timeStamp);
        logList.addLast(log);
    }

    public int delete(int id) {
        Iterator<Log> iterator = logList.iterator();
        while (iterator.hasNext()){
            if(iterator.next().getId()==id){
                logList.remove();
                return 0;
            }
        }
        return -1;
    }

    public int query(int startTime, int endTime) {
        AtomicInteger res = new AtomicInteger(0);
        logList.forEach(
                log -> {
                    if (log.getTimeStamp()>=startTime && log.getTimeStamp()<=endTime){
                        res.getAndIncrement();
                    }
                }
        );
        return res.get();
    }

    public static void main(String[] args) {
        LogSystem logSystem = new LogSystem();
        logSystem.add(2,3);
        System.out.println(logSystem.query(1,4));
        logSystem.delete(2);
        System.out.println(logSystem.query(1,4));
        logSystem.add(2,3);
        logSystem.add(2,3);
        logSystem.add(2,3);
        logSystem.delete(2);
        System.out.println(logSystem.query(3,3));
        System.out.println(logSystem.query(1,2));

    }

}
