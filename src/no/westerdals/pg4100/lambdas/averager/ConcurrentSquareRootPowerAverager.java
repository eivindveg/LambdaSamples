package no.westerdals.pg4100.lambdas.averager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Implementation that uses multiple threads to get the average value
 */
public class ConcurrentSquareRootPowerAverager implements SquareRootPowerAverager {

    private final int threads = Runtime.getRuntime().availableProcessors() * 2;

    @Override
    public double getAverage(final List<? extends Number> numbers, final int power) {
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        List<Future<Double>> futures = new ArrayList<>();

        // Unsure if this is correct. Should be roughly correct for large lists
        final int stepping = numbers.size() / threads;
        for (int i = 0; i < threads; i++) {
            // Split the list into different sublists and execute
            List<? extends Number> subList = numbers.subList(i * stepping, (i + 1) * stepping);
            futures.add(executorService.submit(new LocalAverager(subList, power)));
        }
        executorService.shutdown();

        double sum = 0.0;
        try {

            // Get all the values and shut down the executor forcibly
            for (final Future<Double> future : futures) {
                sum += future.get();
            }
            executorService.shutdownNow();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Interrupted");
        }

        // Turn the different summed averages into a unified average
        return sum / threads;
    }

    /**
     * Worker class for use in thread pools
     */
    public class LocalAverager implements Callable<Double> {

        private final List<? extends Number> numbers;
        private final int power;

        public LocalAverager(final List<? extends Number> numbers, final int power) {
            this.numbers = numbers;
            this.power = power;
        }

        private double getAverage() {
            double sum = 0;
            for (final Number number : numbers) {
                final double sqrt = Math.sqrt(number.doubleValue());
                final double pow = Math.pow(sqrt, power);
                sum += pow;
            }
            return sum / (double) numbers.size();
        }

        @Override
        public Double call() throws Exception {
            return getAverage();
        }
    }
}
