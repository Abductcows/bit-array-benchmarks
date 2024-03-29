package gr.geompokon.bitarray.benchmark.state;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * State objects holding the test sizes
 */
public class TestStates {

    @State(Scope.Benchmark)
    public static class AddTestSizeState {
        @Param({"1000", "100000", "1500000"}) // 1K, 100K, 1M 500K
        public static int ADD_TEST_SIZE;
    }

    @State(Scope.Benchmark)
    public static class GetTestSizeState {
        @Param({"5000000", "50000000", "250000000"}) // 5M, 50M, 250M
        public static int GET_TEST_SIZE;
    }

    @State(Scope.Benchmark)
    public static class SetTestSizeState {
        @Param({"5000000", "50000000", "250000000"}) // 5M, 50M, 250M
        public static int SET_TEST_SIZE;
    }

    @State(Scope.Benchmark)
    public static class RemoveTestSizeState {
        @Param({"1000", "100000", "1000000"}) // 1K, 100K, 1M 500K
        public static int REMOVE_TEST_SIZE;
    }
}
