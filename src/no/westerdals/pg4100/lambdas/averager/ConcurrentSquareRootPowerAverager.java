package no.westerdals.pg4100.lambdas.averager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentSquareRootPowerAverager implements SquareRootPowerAverager {

    private final int threads = Runtime.getRuntime().availableProcessors() * 2;

    @Override
    public double getAverage(final List<? extends Number> numbers, final int power) {
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        List<Future<Double>> futures = new ArrayList<>();

        final int stepping = numbers.size() / threads;
        for (int i = 0; i < threads; i++) {
            List<? extends Number> subList = numbers.subList(i * stepping, (i + 1) * stepping);
            futures.add(executorService.submit(new LocalAverager(subList, power)));
        }

        double sum = 0.0;
        try {

            for (final Future<Double> future : futures) {
                sum += future.get();
            }
            executorService.shutdownNow();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Interrupted");
        }

        return sum / threads;
    }

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
