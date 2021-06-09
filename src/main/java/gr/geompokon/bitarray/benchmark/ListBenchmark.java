/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package gr.geompokon.bitarray.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(5)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 5, timeUnit = TimeUnit.MINUTES)
public abstract class ListBenchmark<E> {

    public static void main(String[] args) throws RunnerException {
        new Runner(
                new OptionsBuilder()
                        .include(".*VS*")
                        .build()
        ).run();
    }

    public abstract static class A<E> extends ListState<E> {
    }

    public abstract static class B<E> extends ListState<E> {
    }


    @Benchmark
    public <T> void aAdd(A<T> listA,
                         Supplier<? extends T> insertions,
                         TestState testState,
                         Blackhole blackhole) {
        listA.obj.add(insertions.get());
        add(listA.obj, insertions, testState, blackhole);
    }

    @Benchmark
    public <T> void aAdd(B<T> listB, Supplier<? extends T> insertions,
                         TestState testState, Blackhole blackhole) {
        listB.obj.add(insertions.get());
        add(listB.obj, insertions, testState, blackhole);
    }

    public <T> void add(List<T> list, Supplier<? extends T> insertions,
                        TestState testState, Blackhole blackhole) {
        for (int i = 1; i < testState.ADD_TEST_SIZE; i++) {
            list.add(
                    testState.rand.nextInt(i),
                    insertions.get());
        }
        blackhole.consume(list);
    }

}
