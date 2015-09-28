package pollux.trialbee.com.pollux;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by philip on 2015-02-27.
 */
public class UniqueInteger {
    private int uniqueId;

    public UniqueInteger(){
        uniqueId = 0;
    }

    public synchronized int getUniqueInteger(){
        return uniqueId++;
    }

}
