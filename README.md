# Hello Multiverse

A contrived program to test concurrent modification of a HashMap that is statically shared between threads.

## System

```
Apache Maven 3.5.3 (3383c37e1f9e9b3bc3df5050c29c8aff9f295297
Java version: 1.8.0_172
```

## Install

```sh
$ git clone https://github.com/mtso/hello-multiverse
$ mvn package
```

## Note

`ReentrantLock` is used for simplicity. A more performant method would
be to use `ReadWriteLock` so that reads do not block each other. If it is possible to
replace the `HashMap` completely, it is also worth looking into `ConcurrentHashMap`.

## Usage

```sh
$ java -cp target/hello-multiverse-1.0-SNAPSHOT.jar io.mtso.app.App [safe|unsafe]
```

The last, optional argument is the string "safe" to run the program with locks.

## References

- [`java.util.concurrent.Lock`](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Lock.html)
- [`java.util.concurrent.ConcurrentMap`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentMap.html)
