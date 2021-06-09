package gr.geompokon.bitarray.benchmark;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.List;

@State(Scope.Thread)
public abstract class ListState<T> {
    public List<T> obj;

    /**
     * Here you should at least instantiate the object with its concrete class
     */
    @Setup(Level.Invocation)
    public abstract void setUp();
}
