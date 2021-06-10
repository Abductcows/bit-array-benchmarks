package gr.geompokon.bitarray.benchmark;

import gr.geompokon.bitarray.benchmark.state.TestStates;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class TestMethods {


    public static <T> void addRandomIndex(List<T> list,
                                          Random indexGenerator,
                                          Supplier<? extends T> insertions) {
        list.add(insertions.get());
        for (int i = 1; i < TestStates.AddTestSizeState.ADD_TEST_SIZE; i++) {
            list.add(
                    indexGenerator.nextInt(i),
                    insertions.get());
        }
    }

    public static <T> void getRandomIndex(List<T> list,
                                          Random indexGenerator,
                                          Blackhole blackhole) {
        int size = list.size();
        for (int i = 0; i < TestStates.GetTestSizeState.GET_TEST_SIZE; i++) {
            int nextIndex = indexGenerator.nextInt(size);
            T current = list.get(nextIndex);
            blackhole.consume(current);
        }
    }

    public static <T> void setRandomIndex(List<T> list,
                                          Random indexGenerator,
                                          UnaryOperator<T> mutator) {
        int size = list.size();
        for (int i = 0; i < TestStates.SetTestSizeState.SET_TEST_SIZE; i++) {
            int nextIndex = indexGenerator.nextInt(size);
            T current = list.get(nextIndex);
            T newElement = mutator.apply(current);
            list.set(nextIndex, newElement);
        }
    }

    public static <T> void removeRandomIndex(List<T> list,
                                             Random indexGenerator) {
        int limit = Math.min(TestStates.RemoveTestSizeState.REMOVE_TEST_SIZE, list.size());
        for (int i = 0; i < limit; i++) {
            int nextIndex = indexGenerator.nextInt(list.size());
            list.remove(nextIndex);
        }
    }

    public static <T> void populateList(List<T> list,
                                        int noOfElements,
                                        Supplier<T> supplier) {
        for (int i = 0; i < noOfElements; i++) {
            list.add(supplier.get());
        }
    }
}
