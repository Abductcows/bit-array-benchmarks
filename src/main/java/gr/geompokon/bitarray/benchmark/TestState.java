package gr.geompokon.bitarray.benchmark;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;

import java.util.Random;

@org.openjdk.jmh.annotations.State(Scope.Thread)
public class TestState {
    public Random rand;

    @Param({"1000", "100000", "1500000"})
    int ADD_TEST_SIZE;

    @Param({"1000", "100000", "1000000"})
    int GET_TEST_SIZE;

    @Param({"1000", "100000", "1000000"})
    int SET_TEST_SIZE;

    @Param({"1000", "100000", "1500000"})
    int REMOVE_TEST_SIZE;

    @Setup(Level.Invocation)
    public void init() {
        rand = new Random();
    }
}
