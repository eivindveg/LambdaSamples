package no.westerdals.pg4100.lambdas.ewclean.models;

public class Item implements Comparable<Item> {

    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }

    @Override
    public int compareTo(final Item o) {
        return Double.compare(this.getValue(), o.getValue());
    }

    @Override
    public String toString() {
        return "Item{" +
                "value=" + value +
                '}';
    }
}
