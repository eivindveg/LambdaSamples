package no.westerdals.pg4100.lambdas.ewclean.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Line implements Comparable<Line> {

    private final List<Item> items = new ArrayList<>();
    private String title;
    private LineValidation lineValidation;

    public Line(final String title) {
        this.title = title;
    }

    public LineValidation getLineValidation() {
        return lineValidation;
    }

    protected void setLineValidation(LineValidation lineValidation) {
        this.lineValidation = lineValidation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItems(Item... items) {
        List<Item> itemList = Arrays.asList(items);
        this.items.addAll(itemList);
    }

    public void addItems(Collection<Item> items) {
        this.items.addAll(items);
    }

    @Override
    public int compareTo(final Line o) {
        return this.title.compareTo(o.title);
    }

    public void addItem(final Item item) {
        items.add(item);
    }
}
