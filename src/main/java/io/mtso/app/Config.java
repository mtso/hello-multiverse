package io.mtso.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Config implements Runnable {
    // `common` is the HashMap that is shared between threads.
    static Map<String, Object> common = new HashMap();
    // The ReentrantLock `lock` is a way to manage concurrent threads similar to
    // `synchronized` constructs, but also usable without scope blocks.
    static Lock lock = new ReentrantLock();
    // Make it more likely to modify concurently with random sleep durations.
    static Random rng = new Random();

    Map<String, Object> config;
    String name;
    Boolean isSafe = false;

    Config(String name, Boolean isSafe, Map<String, Object> config) {
        this.config = config;
        this.name = name;
        this.isSafe = isSafe;
    }

    public void run() {
        if (isSafe) {
            runWithLock();
        } else {
            runWithoutLock();
        }
    }

    public void runWithLock() {
        // Add keys that don't exist
        lock.lock();
        try {
            for (String key : config.keySet()) {
                if (!Config.common.containsKey(key)) {
                    Thread.sleep(rng.nextInt(1000));
                    Config.common.put(key, true);
                }
            }
        } catch(Exception e) {
            lock.unlock();
        } finally {
            lock.unlock();
        }

        // Print all keys of map
        lock.lock();
        try {
            for (String key2 : Config.common.keySet()) {
                Thread.sleep(rng.nextInt(1000));
                System.out.println(String.format("%s: %s %s", name, key2, Config.common.get(key2)));
            }
        } catch(Exception e) {
            lock.unlock();
        } finally {
            lock.unlock();
        }
    }

    public void runWithoutLock() {
        try {
            // Add keys that don't exist
            for (String key : config.keySet()) {
                if (!Config.common.containsKey(key)) {
                    Thread.sleep(rng.nextInt(1000));
                    Config.common.put(key, true);
                }
            }

            // Print all keys of map
            for (String key2 : Config.common.keySet()) {
                Thread.sleep(rng.nextInt(1000));
                System.out.println(String.format("%s: %s %s", name, key2, Config.common.get(key2)));
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
