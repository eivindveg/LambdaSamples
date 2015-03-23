package no.westerdals.pg4100.lambdas.ewclean.workers;

import no.westerdals.pg4100.lambdas.ewclean.models.Item;
import no.westerdals.pg4100.lambdas.ewclean.models.Line;

public class LineValidator {

    public boolean isItemValidForLine(Line line, Item item) {
        return valueWithin10Percent(item.getValue(), line.getLineValidation().getAverage());
    }

    private boolean valueWithin10Percent(Double value, Double reference) {
        return value < reference * 1.10 && value > reference * 0.9;
    }
}
