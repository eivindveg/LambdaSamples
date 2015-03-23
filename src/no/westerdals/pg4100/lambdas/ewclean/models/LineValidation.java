package no.westerdals.pg4100.lambdas.ewclean.models;

public class LineValidation {

    private final Line line;

    private Double average;

    private Double median;

    private Double max;

    private Double min;

    public LineValidation(final Line line) {
        if(line.getLineValidation() != null) {
            throw new UnsupportedOperationException("Line already has a validation object");
        }
        this.line = line;
        line.setLineValidation(this);
    }

    public Line getLine() {
        return line;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(final Double average) {
        this.average = average;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(final Double median) {
        this.median = median;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(final Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(final Double min) {
        this.min = min;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName());
        stringBuilder.append(" for ").append(line.getTitle()).append(":\n");
        stringBuilder.append("\tMinimum: ").append(min).append("\n");
        stringBuilder.append("\tMaximum: ").append(max).append("\n");
        stringBuilder.append("\tMedian: ").append(median).append("\n");
        stringBuilder.append("\tAverage: ").append(average);
        return stringBuilder.toString();
    }
}
