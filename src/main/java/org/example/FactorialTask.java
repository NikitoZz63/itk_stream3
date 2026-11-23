package org.example;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private final int start;
    private final int end;

    public FactorialTask(int n) {
        start = 1;
        end = n;

    }

    public FactorialTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (start > end) {
            return 1L;
        } else if (start == end) {
            return (long) start;
        }
        int mid = start + (end - start) / 2;
        FactorialTask leftTask = new FactorialTask(start, mid);
        FactorialTask rightTask = new FactorialTask(mid + 1, end);

        leftTask.fork();
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();
        return rightResult * leftResult;
    }
}
