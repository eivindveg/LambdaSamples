package no.westerdals.pg4100.lambdas.ewclean.workers;

import no.westerdals.pg4100.lambdas.ewclean.models.Item;
import no.westerdals.pg4100.lambdas.ewclean.models.Line;
import no.westerdals.pg4100.lambdas.ewclean.models.LineValidation;

import java.util.Collections;
import java.util.List;

public class LineValidationBuilder {

    public LineValidationBuilder() {

    }

    public LineValidation update(Line line) {
        List<Item> items = Collections.unmodifiableList(line.getItems());
        LineValidation lineValidation = line.getLineValidation();
        if(lineValidation == null) {
            lineValidation = new LineValidation(line);
        }

        updateMinimum(items, lineValidation);
        updateMaximum(items, lineValidation);
        updateAverage(items, lineValidation);
        updateMedian(items, lineValidation);

        return lineValidation;
    }

    private void updateMedian(final List<Item> items, final LineValidation lineValidation) {
        boolean even = items.size() % 2 == 0;
        long skip = items.size() / 2;
        if(even) {
            skip--;
        }
        items.stream()
                .mapToDouble(Item::getValue)
                .sorted()
                .skip(skip)
                .limit(even ? 2 : 1)
                .average()
                .ifPresent(lineValidation::setMedian);
    }

    private void updateAverage(final List<Item> items, final LineValidation lineValidation) {
        items.stream()
                .mapToDouble(Item::getValue)
                .average()
                .ifPresent(lineValidation::setAverage);
    }

    private void updateMaximum(final List<Item> items, final LineValidation lineValidation) {
        items.stream()
                .mapToDouble(Item::getValue)
                .max()
                .ifPresent(lineValidation::setMax);
    }

    private void updateMinimum(final List<Item> items, final LineValidation lineValidation) {
        items.stream()
                .mapToDouble(Item::getValue)
                .min()
                .ifPresent(lineValidation::setMin);
    }
}
