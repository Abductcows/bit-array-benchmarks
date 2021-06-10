package gr.geompokon.bitarray.benchmark.vsarraylist;

import gr.geompokon.bitarray.BitArray;
import gr.geompokon.bitarray.benchmark.TestMethods;
import gr.geompokon.bitarray.benchmark.state.ListState;
import gr.geompokon.bitarray.benchmark.state.TestStates;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


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
            obj = new BitArray(TestStates.RemoveTestSizeState.REMOVE_TEST_SIZE);
            // add the elements to remove in the benchmark
            TestMethods.populateList(obj,
                    TestStates.RemoveTestSizeState.REMOVE_TEST_SIZE,
                    ThreadLocalRandom.current()::nextBoolean);
        }
    }

    public static class ArrayListState extends ListState<Boolean> {
        @Override
        public void setUp() {
            obj = new ArrayList<>(TestStates.RemoveTestSizeState.REMOVE_TEST_SIZE);
            // add the elements to remove in the benchmark
            TestMethods.populateList(obj,
                    TestStates.RemoveTestSizeState.REMOVE_TEST_SIZE,
                    ThreadLocalRandom.current()::nextBoolean);
        }
    }

    @Benchmark
    public BitArrayState BitArrayRemove(BitArrayState bitArrayState) {
        Random rand = ThreadLocalRandom.current();
        TestMethods.removeRandomIndex(bitArrayState.obj, rand);
        return bitArrayState;
    }

    @Benchmark
    public ArrayListState ArrayListRemove(ArrayListState arrayListState) {
        Random rand = ThreadLocalRandom.current();
        TestMethods.removeRandomIndex(arrayListState.obj, rand);
        return arrayListState;
    }
}
