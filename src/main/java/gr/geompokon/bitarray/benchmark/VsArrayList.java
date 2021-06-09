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

import gr.geompokon.bitarray.benchmark.ListState;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(6)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 5, timeUnit = TimeUnit.MINUTES)
public class VsArrayList {

    public static void main(String[] args) throws RunnerException {
        new Runner(
                new OptionsBuilder()
                        .include(".VsArrayList*")
                        .build()
        ).run();
    }

    public static class BitArray extends ListState<Boolean> {
        public void setUp() {
            obj = new gr.geompokon.bitarray.BitArray(10);
            obj.add(Boolean.FALSE);
        }
    }

    public static class ArrayList extends ListState<Boolean> {
        public void setUp() {
            obj = new java.util.ArrayList<>(10);
            obj.add(Boolean.FALSE);
        }
    }

    @State(Scope.Thread)
    public static class vars {
        public Random rand;

        @Param({"1000", "100000", "1500000"})
        int TEST_SIZE;

        @Setup(Level.Invocation)
        public void init() {
            rand = new Random();
        }
    }

    @Benchmark
    public void bitArrayRandomAdd(BitArray bitArray, vars vars, Blackhole blackhole) {
        for (int i = 0; i < vars.TEST_SIZE; i++) {
            bitArray.obj.add(
                    vars.rand.nextInt(bitArray.obj.size()),
                    vars.rand.nextBoolean());
        }
        blackhole.consume(bitArray.obj);
    }

    @Benchmark
    public void boolArrayRandomAdd(ArrayList ArrayList, vars vars, Blackhole blackhole) {
        for (int i = 0; i < vars.TEST_SIZE; i++) {
            ArrayList.obj.add(
                    vars.rand.nextInt(ArrayList.obj.size()),
                    vars.rand.nextBoolean());
        }
        blackhole.consume(ArrayList.obj);
    }

}
