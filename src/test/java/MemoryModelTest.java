/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryModelTest {
    private static final int cycles = 10000;
    private static final int threads = 16;

    public static void main(String[] args) throws InterruptedException {
        MemoryModelTest test = new MemoryModelTest();

        Set<Thread> threadList = Sets.newHashSet();
        for (int i = 0; i < threads; i++) {
            Thread thread = new Thread(test.newT1Runnable());
            threadList.add(thread);
            thread.start();
        }

        test.awaitFinish(threadList);

        Thread t2 = new Thread(test.newT2Runnable());
        t2.start();
        t2.join();

        int runs = test.getActualCount();
        System.out.format("%d runs, got %d back%n", runs, test.getFinalCount());
        System.out.format("Matches? %s", test.getFinalCount() == runs);
    }

    public T1Runnable newT1Runnable() {
        return new T1Runnable();
    }

    public T2Runnable newT2Runnable() {
        return new T2Runnable();
    }

    public int getFinalCount() {
        return this.read;
    }

    public int getActualCount() {
        return this.finalCount.get();
    }

    public void awaitFinish() throws InterruptedException {
        this.latch.await();
    }

    public void awaitFinish(Iterable<Thread> set) throws InterruptedException {
        for (Thread thread : set) thread.join();
    }

    private final CountDownLatch latch = new CountDownLatch(threads);

    private final ThreadSafeCounter state = new ThreadSafeCounter();
    private volatile int read;
    private final AtomicInteger finalCount = new AtomicInteger();

    private class T1Runnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < cycles; i++) {
                int subCycles = ThreadLocalRandom.current().nextInt(10_000);

                for (int j = 0; j < subCycles; j++) {
                    MemoryModelTest.this.state.increment();
                }

                Thread.yield();
                MemoryModelTest.this.finalCount.addAndGet(subCycles);
            }

            MemoryModelTest.this.latch.countDown();
        }
    }

    private class T2Runnable implements Runnable {
        @Override
        public void run() {
            MemoryModelTest.this.read = MemoryModelTest.this.state.read();
        }
    }

    private class ThreadSafeCounter {
        private volatile int integer;

        public synchronized void increment() {
            this.integer++;
        }

        public int read() {
            return this.integer;
        }
    }
}