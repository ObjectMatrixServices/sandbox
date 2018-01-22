package com.oms.sandbox.parallelism.strategy.parallel.future;

import com.oms.sandbox.parallelism.model.Generator;
import com.oms.sandbox.parallelism.strategy.GeneratorStrategy;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toList;

/**
 * Uses ForkJoinPool's Common Pool - a Thread Pool shared by the entire application.
 * @author omsivanesan
 */

public class GeneratorCompletableFuturesStrategy extends GeneratorStrategy {

    @Override
    public void displayFuelLevelsAndTheirAverage(List<Generator> generators) {
        out.println("Using GeneratorCompletableFuturesStrategy:");
        long start = currentTimeMillis();

        List<CompletableFuture<Double>> futures = generators.stream()
                                                            .map(g -> supplyAsync(g::getCurrentFuelLevel))
                                                            .collect(toList());

        List<Double> currentFuelLevels = futures.stream()
                                                .map(CompletableFuture::join)
                                                .collect(toList());

        displayResults(generators.size(), currentFuelLevels, (currentTimeMillis() - start));
    }
}