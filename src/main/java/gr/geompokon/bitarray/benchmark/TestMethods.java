package gr.geompokon.bitarray.benchmark;

import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class TestMethods {


    public static <T> void addRandomIndex(List<T> list,
                                          Random indexGenerator,
                                          int iterations,
                                          Supplier<? extends T> insertions) {
        list.add(insertions.get());
        for (int i = 1; i < iterations; i++) {
            list.add(
                    indexGenerator.nextInt(i),
                    insertions.get());
        }
    }

    public static <T> void getRandomIndex(List<T> list,
                                          Random indexGenerator,
                                          int iterations,
                                          Blackhole blackhole) {
        int size = list.size();
        for (int i = 0; i < iterations; i++) {
            int nextIndex = indexGenerator.nextInt(size);
            T current = list.get(nextIndex);
            blackhole.consume(current);
        }
    }

    /**
     * Performs <i>iterations</i> set operations in the argument list at indices supplied by {@code indexGenerator}.
     * For each element selected, {@code mutator} is applied to it and then it is replaced by the result.
     */
    public static <T> void setRandomIndex(List<T> list,
                                          Random indexGenerator,
                                          int iterations,
                                          UnaryOperator<T> mutator) {
        int size = list.size();
        for (int i = 0; i < iterations; i++) {
            int nextIndex = indexGenerator.nextInt(size);
            T current = list.get(nextIndex);
            T newElement = mutator.apply(current);
            list.set(nextIndex, newElement);
        }
    }

    /**
     * Performs {@code iterations} random index insertions or until list is empty
     */
    public static <T> void removeRandomIndex(List<T> list,
                                             Random indexGenerator,
                                             int iterations) {
        int limit = Math.min(iterations, list.size());
        for (int i = 0; i < limit; i++) {
            int nextIndex = indexGenerator.nextInt(list.size());
            list.remove(nextIndex);
        }
    }

    /**
     * Populates the list with {@code noOfElements} elements using the argument supplier
     */
    public static <T> void populateList(List<T> list,
                                        int noOfElements,
                                        Supplier<T> supplier) {
        for (int i = 0; i < noOfElements; i++) {
            list.add(supplier.get());
        }
        System.out.println("List populated; size = " + list.size());
    }
}
