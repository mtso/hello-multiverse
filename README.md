# Hello Multiverse

A contrived program to test concurrent modification of a HashMap that is statically shared between threads.

## Note

ReentrantLock is used for simplicity. A more performant method would
be to use ReadWriteLock so that reads do not block each other.

## Usage

```sh
$ java -cp target/hello-multiverse-1.0-SNAPSHOT.jar io.mtso.app.App [safe|unsafe]
```

The last, optional argument is the string "safe" to run the program with locks.
