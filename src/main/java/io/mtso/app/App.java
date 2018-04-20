package io.mtso.app;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello, multiverse!
 *
 *     A program to test modification of a shared HashMap between threads.
 *
 * Note:
 *
 *     ReentrantLock is used for simplicity. A more performant method would
 *     be to use ReadWriteLock so that reads do not block each other.
 *
 * Usage:
 *
 *     $ java -cp target/hello-multiverse-1.0-SNAPSHOT.jar io.mtso.app.App [safe|unsafe]
 *
 *     The last, optional argument is the string "safe" to run the program with locks.
 */
public class App
{
    public static void main( String[] args ) throws InterruptedException
    {
        Boolean isSafe = args.length > 0 && args[0].equals("safe") || false;

        System.out.printf("Hello Multiverse v1\nExecuting %s lock...\n", isSafe ? "WITH" : "WITHOUT");

        Map<String, Object> first = new HashMap();
        Map<String, Object> second = new HashMap();

        // Fill first and second maps
        for (int i = 0; i < 3; i++) {
            first.put(String.format("first-%d", i), true);
            second.put(String.format("second-%d", i), true);
        }

        (new Thread(new Config("first", isSafe, first))).start();
        (new Thread(new Config("second", isSafe, second))).start();
    }
}
