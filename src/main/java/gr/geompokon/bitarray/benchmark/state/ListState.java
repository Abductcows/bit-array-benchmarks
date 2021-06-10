package gr.geompokon.bitarray.benchmark.state;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.List;

/**
 * State object holding the {@link List} to be tested
 * @param <E> the generic parameter of the list
 */
@State(Scope.Thread)
public abstract class ListState<E> {
    public List<E> obj;

    /**
     * Here you should at least instantiate the object with its concrete class
     */
    @Setup(Level.Invocation)
    public abstract void setUp();
}
