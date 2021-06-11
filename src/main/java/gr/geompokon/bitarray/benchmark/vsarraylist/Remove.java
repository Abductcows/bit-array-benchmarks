package gr.geompokon.bitarray.benchmark.vsarraylist;

import gr.geompokon.bitarray.BitArray;
import gr.geompokon.bitarray.benchmark.TestMethods;
import gr.geompokon.bitarray.benchmark.state.ListState;
import gr.geompokon.bitarray.benchmark.state.TestStates.RemoveTestSizeState;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(5)
@Warmup(iterations = 0, time = 1, timeUnit = TimeUnit.NANOSECONDS)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.NANOSECONDS)
public class Remove {

    public static void main(String[] args) throws RunnerException {
        new Runner(
                new OptionsBuilder()
                        .include(".*Remove*")
                        .build()
        ).run();
    }

    public static class BitArrayState extends ListState<Boolean> {
        @Override
        public void setUp() {
            int elementsToAdd = RemoveTestSizeState.REMOVE_TEST_SIZE;
            obj = new BitArray(elementsToAdd);
            // add the elements to remove in the benchmark
            TestMethods.populateList(obj, elementsToAdd,
                    ThreadLocalRandom.current()::nextBoolean);
        }
    }

    public static class ArrayListState extends ListState<Boolean> {
        @Override
        public void setUp() {
            int elementsToAdd = RemoveTestSizeState.REMOVE_TEST_SIZE;
            obj = new ArrayList<>(elementsToAdd);
            // add the elements to remove in the benchmark
            TestMethods.populateList(obj, elementsToAdd,
                    ThreadLocalRandom.current()::nextBoolean);
        }
    }

    @Benchmark
    public BitArrayState BitArrayRemove(BitArrayState bitArrayState, RemoveTestSizeState testSizeState) {
        Random rand = ThreadLocalRandom.current();
        TestMethods.removeRandomIndex(bitArrayState.obj, rand, RemoveTestSizeState.REMOVE_TEST_SIZE);
        return bitArrayState;
    }

    @Benchmark
    public ArrayListState ArrayListRemove(ArrayListState arrayListState, RemoveTestSizeState testSizeState) {
        Random rand = ThreadLocalRandom.current();
        TestMethods.removeRandomIndex(arrayListState.obj, rand, RemoveTestSizeState.REMOVE_TEST_SIZE);
        return arrayListState;
    }
}
