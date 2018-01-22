package com.oms.sandbox.parallelism.strategy.parallel.future;

import com.oms.sandbox.parallelism.model.Generator;
import com.oms.sandbox.parallelism.strategy.GeneratorStrategy;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toList;

/**
 * Uses Custom Thread Pool instead of the ForkJoinPool's Common Pool.
 *
 * Can use the Fixed Thread Pool, where you specify the number of threads or can use the Cached Thread Pool, which
 * will create new Threads as needed but will reuse previously created Threads.
 *
 * @author omsivanesan
 */

public class GeneratorCompletableFuturesWithCustomExecutorStrategy extends GeneratorStrategy {

    @Override
    public void displayFuelLevelsAndTheirAverage(List<Generator> generators) {
        out.println("Using GeneratorCompletableFuturesWithCustomExecutorStrategy:");
        long start = currentTimeMillis();

        ExecutorService executor = Executors.newCachedThreadPool();
        //        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Double>> futures = generators.stream()
                                                            .map(g -> supplyAsync(g::getCurrentFuelLevel, executor))
                                                            .collect(toList());
        List<Double> currentFuelLevels = futures.stream()
                                                .map(CompletableFuture::join)
                                                .collect(toList());

        displayResults(generators.size(), currentFuelLevels, (currentTimeMillis() - start));
        executor.shutdown();
    }
}
